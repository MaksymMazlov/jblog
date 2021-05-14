package ua.org.jblog.service.mapper;

import org.springframework.stereotype.Service;
import ua.org.jblog.domain.Post;
import ua.org.jblog.dto.PostDto;

import java.time.format.DateTimeFormatter;

@Service
public class ConverterPostToPostDto
{
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public PostDto convertToDto(Post post)
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
}
