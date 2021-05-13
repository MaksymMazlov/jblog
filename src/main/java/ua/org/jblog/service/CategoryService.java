package ua.org.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.org.jblog.domain.Category;
import ua.org.jblog.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService
{
    @Autowired
    private CategoryRepository categoryRepository;

    public void save(Category category)
    {
        categoryRepository.save(category);
    }

    public List<Category> getAll()
    {
        return categoryRepository.findAll();
    }

    public String categoryNameById(int catId)
    {
        return categoryRepository.findById(catId).getName();

    }
}
