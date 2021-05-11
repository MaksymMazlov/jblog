package ua.org.jblog.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.org.jblog.domain.UserLike;
import ua.org.jblog.domain.UserLikePK;

@Repository
public interface UserLikeRepository extends CrudRepository<UserLike, UserLikePK>
{
}
