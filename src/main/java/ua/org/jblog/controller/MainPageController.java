package ua.org.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ua.org.jblog.dto.PostDto;
import ua.org.jblog.service.CategoryService;
import ua.org.jblog.service.PostService;

import java.util.List;

@Controller
public class MainPageController extends AbstractPageController
{
    @Autowired
    private PostService postService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String getPosts(@RequestParam(required = false, defaultValue = "1") int page, Model model)
    {
        addCommonData(model);
        List<PostDto> posts = postService.getForPage(page);
        model.addAttribute("posts_list", posts);
        model.addAttribute("page", page);
        model.addAttribute("pages", postService.countPage());

        return "index";
    }

    @GetMapping("/category/{id}")
    public String reqSearchCategory(@PathVariable("id") int catId, Model model)
    {
        addCommonData(model);
        List<PostDto> posts = postService.getAllByCategoryId(catId);
        model.addAttribute("search_posts_list", posts);
        model.addAttribute("searchCat", categoryService.categoryNameById(catId));
        return "searchPage";
    }

}