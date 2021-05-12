package ua.org.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.org.jblog.domain.Post;
import ua.org.jblog.domain.UserLike;
import ua.org.jblog.domain.UserLikePK;
import ua.org.jblog.dto.PostDto;
import ua.org.jblog.repository.PostRepository;
import ua.org.jblog.repository.UserLikeRepository;

import java.time.LocalDateTime;

@Service
public class LikePostService
{
    @Autowired
    private UserService userService;
    @Autowired
    private UserLikeRepository likeRepository;
    @Autowired
    private PostRepository postRepository;

    public void createLike(PostDto postDto)
    {
        UserLikePK likePK = new UserLikePK(userService.currentUser().getId(),postDto.getId());
        if (likeRepository.existsById(likePK))
        {
            return;
        }

        Post post = postRepository.findById(postDto.getId());
        post.setLikes(post.getLikes() + postDto.getLikes());
        postRepository.save(post);

        UserLike like = new UserLike();
        like.setId(likePK);
        like.setCreated(LocalDateTime.now());
        likeRepository.save(like);
    }

    public boolean likeExist(int idPost)
    {
        UserLikePK likePK = new UserLikePK(userService.currentUser().getId(),idPost);
        return likeRepository.existsById(likePK);
    }
}