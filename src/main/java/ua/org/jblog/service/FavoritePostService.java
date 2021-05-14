package ua.org.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.org.jblog.domain.FavoritePost;
import ua.org.jblog.domain.FavoritePostPK;
import ua.org.jblog.domain.Post;
import ua.org.jblog.dto.PostDto;
import ua.org.jblog.repository.FavoritePostRepository;
import ua.org.jblog.repository.PostRepository;
import ua.org.jblog.service.mapper.ConverterPostToPostDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FavoritePostService
{
    @Autowired
    private FavoritePostRepository favoritePostRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ConverterPostToPostDto postToPostDto;

    public void addFavoritePost(int postId)
    {
        FavoritePostPK fpk = new FavoritePostPK();
        fpk.setPostId(postId);
        fpk.setUserId(userService.currentUser().getId());

        FavoritePost fPost = new FavoritePost();
        fPost.setId(fpk);
        fPost.setCreated(LocalDateTime.now());
        favoritePostRepository.save(fPost);
    }

    public boolean isFavorite(int id)
    {
        FavoritePostPK favoritePostPK = new FavoritePostPK();
        favoritePostPK.setPostId(id);
        favoritePostPK.setUserId(userService.currentUser().getId());
        return favoritePostRepository.existsById(favoritePostPK);
    }

    public List<PostDto> getAllFavPost()
    {
        List<Integer> listIdPosts = favoritePostRepository.findIdPostIdByIdUserId(userService.currentUser().getId());

        List<Post> postList = postRepository.findByIdInOrderByCreatedDesc(listIdPosts);
        List<PostDto> listPostDto = new ArrayList<>();
        for (Post post : postList)
        {
            PostDto postDto = postToPostDto.convertToDto(post);
            listPostDto.add(postDto);
        }
        return listPostDto;
    }
}
