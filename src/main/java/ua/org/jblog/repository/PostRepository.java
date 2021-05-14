package ua.org.jblog.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.org.jblog.domain.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>
{
    List<Post> findAllByOrderByIdDesc();

    List<Post> findAllByOrderByIdDesc(Pageable page);

    Post findById(int id);

    List<Post> findAllByFullTextLike(String str);

    List<Post> findAllByCategoryId(int catId);

    List<Post> findByIdInOrderByCreatedDesc(List<Integer> listIdPosts);
}

