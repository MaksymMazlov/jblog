package ua.org.jblog.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.org.jblog.domain.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer>
{
    List<Category> findAll();

    Category findById(int catId);
}
