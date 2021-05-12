package ua.org.jblog.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.org.jblog.domain.UserCommentLike;
import ua.org.jblog.domain.UserCommentLikePK;

import java.util.List;

@Repository
public interface UserCommentLikeRepository extends CrudRepository<UserCommentLike, UserCommentLikePK>
{
    List<UserCommentLike> findByIdUserIdAndIdCommentIdIn(int userId,List<Integer>commentId);
}
