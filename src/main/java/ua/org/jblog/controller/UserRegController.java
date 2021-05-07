package ua.org.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.org.jblog.domain.Category;
import ua.org.jblog.dto.UserRegDto;
import ua.org.jblog.service.CategoryService;
import ua.org.jblog.service.UserService;

import java.util.List;

@Controller
public class UserRegController
{
    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/registration")
    public String showPageReg(Model model)
    {
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categoriesToHTML",categories);
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute UserRegDto userRegDto)
    {
        userService.registration(userRegDto);
        return "redirect:/login";
    }

}
