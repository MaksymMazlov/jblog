package ua.org.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import ua.org.jblog.domain.Category;
import ua.org.jblog.service.CategoryService;

import java.util.List;

public abstract class AbstractPageController
{
    @Autowired
    private CategoryService categoryService;

    protected void addCommonData(Model model)
    {
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categoriesToHTML", categories);

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
