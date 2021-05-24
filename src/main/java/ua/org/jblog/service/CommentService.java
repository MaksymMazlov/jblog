package ua.org.jblog.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.org.jblog.Exception.InvalidFieldException;
import ua.org.jblog.Exception.NotFoundException;
import ua.org.jblog.domain.Comment;
import ua.org.jblog.dto.CommentDto;
import ua.org.jblog.repository.CommentRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentService.class);
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CacheService cacheService;

    public void createComment(String comment, int idPost)
    {
        if (StringUtils.isBlank(comment))
        {
            throw new InvalidFieldException("Комментарий не может быть пустым");
        }

        Comment newComment = new Comment();
        newComment.setComment(comment);
        newComment.setPostId(idPost);
        newComment.setCreated(LocalDateTime.now());
        newComment.setUserId(userService.currentUser().getId());
        commentRepository.save(newComment);
        cacheService.cleanComments();
        cacheService.cleanTopCommentsCache();
        LOGGER.info("In createComment: created comment: {}", comment);
    }

    public List<CommentDto> getAllComment(int idPost)
    {
        List<CommentDto> commentsCache = cacheService.getCommentsCache(idPost);
        if (commentsCache != null)
        {
            return commentsCache;
        }
        List<Comment> commentsFromDb = commentRepository.findAllByPostIdOrderByCreatedDesc(idPost);
        List<CommentDto> allComments = new ArrayList<>();
        for (Comment comment : commentsFromDb)
        {
            CommentDto commentDto = new CommentDto();
            commentDto.setId(comment.getId());
            commentDto.setAuthorComment(comment.getUser().getName());
            commentDto.setComment(comment.getComment());
            commentDto.setCreated(comment.getCreated().format(DATE_TIME_FORMATTER));
            commentDto.setPostId(comment.getPostId());
            commentDto.setLikes(comment.getLikes());
            allComments.add(commentDto);
        }
        cacheService.addCommentsCache(idPost, allComments);
        return allComments;
    }

    public void delComment(int commentId)
    {
        Comment comment = commentRepository.findById(commentId);
        if (comment == null)
        {
            throw new NotFoundException("Комментарий не существует");
        }

        if (comment.getUserId() == userService.currentUser().getId())
        {
            commentRepository.deleteById(commentId);
            cacheService.cleanComments();
            LOGGER.info("In delComment: delete comment: ID {}", commentId);
        }
        else
        {
            LOGGER.error("In delComment: delete comment is impossible!");
        }
    }

    public void updateComment(String comment, int commentId)

    {
        Comment oldComment = commentRepository.findById(commentId);
        if (comment == null)
        {
            throw new NotFoundException("Комментарий не существует");
        }

        if (oldComment.getUserId() == userService.currentUser().getId())
        {
            oldComment.setComment(comment);
            commentRepository.save(oldComment);
            cacheService.cleanComments();
            LOGGER.info("In updateComment: update comment:  {}", comment);
        }
        else
        {
            LOGGER.error("In updateComment: update comment is impossible!");
        }
    }

    public List<CommentDto> getTopComments()
    {
        List<CommentDto> topComments = cacheService.getTopCommentsCache();
        if (!topComments.isEmpty())
        {
            return topComments;
        }
        List<Comment> top10ByOrderByLikesDesc = commentRepository.findTop10ByOrderByLikesDesc();
        List<CommentDto> top10CommentsDto = new ArrayList<>();
        for (Comment comment : top10ByOrderByLikesDesc)
        {
            CommentDto commentDto = new CommentDto();
            commentDto.setId(comment.getId());
            commentDto.setAuthorComment(comment.getUser().getName());
            if (comment.getComment().length() > 30)
            {
                commentDto.setComment(comment.getComment().substring(0, 30) + "…");
            }
            else
            {
                commentDto.setComment(comment.getComment());
            }

            commentDto.setCreated(comment.getCreated().format(DATE_TIME_FORMATTER));
            commentDto.setPostId(comment.getPostId());
            commentDto.setLikes(comment.getLikes());
            top10CommentsDto.add(commentDto);
        }
        cacheService.addTopCommentsCache(top10CommentsDto);
        return top10CommentsDto;
    }
}
