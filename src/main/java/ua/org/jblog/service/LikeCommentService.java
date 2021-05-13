package ua.org.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.org.jblog.Exception.NullException;
import ua.org.jblog.domain.Comment;
import ua.org.jblog.domain.UserCommentLike;
import ua.org.jblog.domain.UserCommentLikePK;
import ua.org.jblog.repository.CommentRepository;
import ua.org.jblog.repository.UserCommentLikeRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LikeCommentService
{

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserCommentLikeRepository commentLikeRepository;
    @Autowired
    private UserService userService;

    public Set<Integer> getLikedCommentsByUser(int idUser, int idPost)
    {
        List<Comment> commentListFromDB = commentRepository.findAllByPostIdOrderByCreatedDesc(idPost);
        List<Integer> commentId = commentListFromDB.stream().map(Comment::getId).collect(Collectors.toList());
        List<UserCommentLike> likedComments = commentLikeRepository.findByIdUserIdAndIdCommentIdIn(idUser, commentId);
        return likedComments.stream().map(e -> e.getId().getCommentId()).collect(Collectors.toSet());
    }

    public void sentenceLikeCom(int commentId, int likes)
    {
        UserCommentLikePK commentLikePK = new UserCommentLikePK(userService.currentUser().getId(), commentId);

        if (commentLikeRepository.existsById(commentLikePK))
        {
            return;
        }

        Comment comment = commentRepository.findById(commentId);
        if (comment == null)
        {
            throw new NullException("Комментарий не существует");
        }
        comment.setLikes(comment.getLikes() + likes);
        commentRepository.save(comment);

        UserCommentLike commentLike = new UserCommentLike();
        commentLike.setId(commentLikePK);
        commentLike.setCreated(LocalDateTime.now());
        commentLikeRepository.save(commentLike);
    }
}