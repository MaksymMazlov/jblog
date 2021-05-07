package ua.org.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.org.jblog.domain.Category;
import ua.org.jblog.domain.Role;
import ua.org.jblog.service.CategoryService;
import ua.org.jblog.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class CategoryController
{
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    @GetMapping("/category")
    public String createCategoryPage(Model model, Principal principal)
    {
        if (userService.currentUser() == null || userService.currentUser().getRole() != Role.ADMIN)
        {
            model.addAttribute("error", "Access denied");
        }
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categoriesToHTML", categories);
        model.addAttribute("principal", principal);
        return "category";
    }

    @PostMapping("/category")
    public String addCategory(@ModelAttribute Category category, Principal principal, Model model)
    {
        if (userService.currentUser() == null || userService.currentUser().getRole() != Role.ADMIN)
        {
            model.addAttribute("error", "Access denied");
        }
        else
        {
            categoryService.save(category);
        }
        List<Category> categories = categoryService.getAll();
        model.addAttribute("principal", principal);
        model.addAttribute("categoriesToHTML", categories);

        return "category";
    }


}
