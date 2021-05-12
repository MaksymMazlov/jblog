package ua.org.jblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.org.jblog.domain.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>
{
    List<Comment> findAllByPostIdOrderByCreatedDesc(int idPost);

    Comment findById(int id);
}
