package ua.org.jblog.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.org.jblog.domain.FavoritePost;
import ua.org.jblog.domain.FavoritePostPK;

import java.util.List;

@Repository
public interface FavoritePostRepository extends CrudRepository<FavoritePost, FavoritePostPK>
{

    @Query("select id.postId from FavoritePost where id.userId=:id")
    List<Integer> findIdPostIdByIdUserId(@Param("id") int id);

}
