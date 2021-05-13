package ua.org.jblog.service;

import com.backendless.Backendless;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.org.jblog.Exception.EmptyOrNullFieldException;
import ua.org.jblog.domain.Post;
import ua.org.jblog.dto.CreatePostDto;
import ua.org.jblog.dto.PostDto;
import ua.org.jblog.repository.PostRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PostService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(PostService.class);
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private PostRepository postRepository;

    public void createPost(int userId, CreatePostDto newPost, byte[] bytes, String fileName)
    {
        String title = newPost.getTitle();
        if (StringUtils.isBlank(title))
        {
            throw new EmptyOrNullFieldException("Поле Title не может быть пустым");
        }
        String text = newPost.getText();
        if (StringUtils.isBlank(text))
        {
            throw new EmptyOrNullFieldException("Поле Text не может быть пустым");
        }

        Post post = new Post();
        post.setTitle(title);
        post.setCategoryId(newPost.getCategoryId());
        post.setFullText(text);
        post.setShortText(StringUtils.left(text, 255));
        post.setTag(newPost.getTag());
        post.setCreated(LocalDateTime.now());
        post.setUserId(userId);
        LOGGER.info("In createPost: created post with Title: {}", title);
        String imgId = savePhoto(bytes, fileName, post);
        post.setImg(imgId);
        postRepository.save(post);
    }

    private String savePhoto(byte[] bytes, String fileName, Post post)
    {
        String uuid = UUID.randomUUID() + fileName;
        return Backendless.Files.saveFile("/upload/img", uuid, bytes);
    }

    public List<PostDto> getAll()
    {
        List<Post> postList = postRepository.findAllByOrderByIdDesc();
        List<PostDto> listPostDto = new ArrayList<>();
        for (Post post : postList)
        {
            PostDto postDto = convertToDto(post);
            listPostDto.add(postDto);
        }
        return listPostDto;
    }

    public List<PostDto> getForPage(int page)
    {
        PageRequest pageRequest = PageRequest.of(page - 1, 5);
        List<Post> postList = postRepository.findAllByOrderByIdDesc(pageRequest);
        List<PostDto> listPostDto = new ArrayList<>();
        for (Post post : postList)
        {
            PostDto postDto = convertToDto(post);
            listPostDto.add(postDto);
        }
        return listPostDto;
    }

    public int countPage()
    {
        List<Post> all = postRepository.findAllByOrderByIdDesc();

        int pages = all.size() / 5 + (all.size() % 5 > 0 ? 1 : 0);
        return Math.max(pages, 1);
    }

    public PostDto getPost(int id)
    {
        Post post = postRepository.findById(id);
        int views = post.getViews() + 1;
        post.setViews(views);
        postRepository.save(post);
        PostDto postDto = convertToDto(post);
        return postDto;
    }

    private PostDto convertToDto(Post post)
    {
        PostDto postDto = new PostDto();

        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setFullText(post.getFullText());
        postDto.setShortText(post.getShortText());
        postDto.setTag(post.getTag());
        postDto.setViews(post.getViews());
        postDto.setLikes(post.getLikes());
        postDto.setCreated(post.getCreated().format(DATE_TIME_FORMATTER));
        postDto.setCategoryName(post.getCategory().getName());
        postDto.setAuthor(post.getUser().getName());
        postDto.setImg(post.getImg());
        return postDto;
    }

    public List<PostDto> getAllBySearch(String searchName)
    {
        if (StringUtils.isBlank(searchName))
        {
            throw new EmptyOrNullFieldException("Not found search name!");
        }
        String searchFromBD = "%" + searchName + "%";

        List<Post> postList = postRepository.findAllByFullTextLike(searchFromBD);
        List<PostDto> listPostDto = new ArrayList<>();
        for (Post post : postList)
        {
            PostDto postDto = convertToDto(post);
            listPostDto.add(postDto);
        }
        return listPostDto;
    }

    public List<PostDto> getAllByCategoryId(int catId)
    {
        List<Post> postList = postRepository.findAllByCategoryId(catId);
        List<PostDto> listPostDto = new ArrayList<>();
        for (Post post : postList)
        {
            PostDto postDto = convertToDto(post);
            listPostDto.add(postDto);
        }
        return listPostDto;
    }
}
