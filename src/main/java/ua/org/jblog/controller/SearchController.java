package ua.org.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.org.jblog.Exception.EmptyOrNullFieldException;
import ua.org.jblog.domain.Category;
import ua.org.jblog.dto.PostDto;
import ua.org.jblog.service.CategoryService;
import ua.org.jblog.service.PostService;

import java.util.List;

@Controller
public class SearchController
{
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PostService postService;

    @GetMapping("/search")
    public String reqSearch(String s, Model model)
    {
        try
        {
            List<Category> categories = categoryService.getAll();
            List<PostDto> posts = postService.getAllBySearch(s);
            model.addAttribute("search_posts_list", posts);
            model.addAttribute("categoriesToHTML", categories);
            model.addAttribute("searchName", s);
            return "searchPage";
        }
        catch (EmptyOrNullFieldException e)
        {

            return "redirect:/";
        }
    }
}
