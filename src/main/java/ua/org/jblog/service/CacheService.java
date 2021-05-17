package ua.org.jblog.service;

import org.springframework.stereotype.Service;
import ua.org.jblog.domain.Category;
import ua.org.jblog.dto.PostDto;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class CacheService
{
    private final Map<Integer, List<PostDto>> postByPageCache = new ConcurrentHashMap<>();
    private final List<Category> categoriesCache = new CopyOnWriteArrayList<>();

    public List<PostDto> getPostByPageFromCache(int pageNumber)
    {
        return postByPageCache.get(pageNumber);
    }

    public void addPostByPageToCache(int pageNumber, List<PostDto> posts)
    {
        postByPageCache.put(pageNumber, posts);
    }

    public void cleanPostByPageCache()
    {
        postByPageCache.clear();
    }

    public List<Category> getCategoriesCache()
    {
        return categoriesCache;
    }

    public void addCategoriesCache(List<Category> categories)
    {
        categoriesCache.addAll(categories);
    }

    public void cleanCategories()
    {
        categoriesCache.clear();
    }
}
