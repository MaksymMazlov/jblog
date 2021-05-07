package ua.org.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.org.jblog.domain.Category;
import ua.org.jblog.service.CategoryService;

import java.util.List;

@Controller
public class LoginPageController
{
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/login")
    public String login(Model model)
    {
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categoriesToHTML", categories);
        return "login";
    }
}
