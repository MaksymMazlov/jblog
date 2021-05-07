package ua.org.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.org.jblog.domain.Category;
import ua.org.jblog.dto.PostDto;
import ua.org.jblog.service.CategoryService;
import ua.org.jblog.service.PostService;

import java.security.Principal;
import java.util.List;

@Controller
public class MainPageController
{
    @Autowired
    private PostService postService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String getPosts(@RequestParam(required = false, defaultValue = "0") int page, Model model, Principal principal)
    {
        List<Category> categories = categoryService.getAll();
        List<PostDto> posts = postService.getForPage(page);
        model.addAttribute("posts_list", posts);
        model.addAttribute("categoriesToHTML", categories);
        model.addAttribute("principal", principal);
        model.addAttribute("page", page);
        model.addAttribute("pages", postService.counPage());

        return "index";
    }


}