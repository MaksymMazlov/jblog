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
    @Autowired
    private CacheService cacheService;

    public void save(Category category)
    {
        categoryRepository.save(category);
        cacheService.cleanCategories();
    }

    public List<Category> getAll()
    {
        List<Category> categoriesCache = cacheService.getCategoriesCache();
        if (!categoriesCache.isEmpty())
        {
            return categoriesCache;
        }

        List<Category> categoriesFromDb = categoryRepository.findAll();
        cacheService.addCategoriesCache(categoriesFromDb);
        return categoriesFromDb;
    }

    public String categoryNameById(int catId)
    {
        return categoryRepository.findById(catId).getName();
    }
}
