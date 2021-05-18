package ua.org.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import ua.org.jblog.domain.Category;
import ua.org.jblog.dto.PostDto;
import ua.org.jblog.service.CategoryService;
import ua.org.jblog.service.PostService;

import java.util.List;

public abstract class AbstractPageController
{
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PostService topPosts;

    protected void addCommonData(Model model)
    {
        List<Category> categories = categoryService.getAll();
        List<PostDto> posts = topPosts.getTopPosts();
        model.addAttribute("categoriesToHTML", categories);
        model.addAttribute("topPost", posts);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null)
        {
            Object principal = authentication.getPrincipal();
            if (principal instanceof User)
            {
                model.addAttribute("principal", authentication);
            }

        }
    }
}
