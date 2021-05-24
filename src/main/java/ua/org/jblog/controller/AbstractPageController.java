package ua.org.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import ua.org.jblog.domain.Category;
import ua.org.jblog.dto.CommentDto;
import ua.org.jblog.dto.PostDto;
import ua.org.jblog.service.CategoryService;
import ua.org.jblog.service.CommentService;
import ua.org.jblog.service.PostService;

import java.util.List;

public abstract class AbstractPageController
{
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;

    protected void addCommonData(Model model)
    {
        List<Category> categories = categoryService.getAll();
        List<PostDto> posts = postService.getTopPosts();
        List<CommentDto> comments = commentService.getTopComments();
        model.addAttribute("categoriesToHTML", categories);
        model.addAttribute("topPost", posts);
        model.addAttribute("topComments", comments);

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
