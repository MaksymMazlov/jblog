package ua.org.jblog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.org.jblog.Exception.EmptyOrNullFieldException;
import ua.org.jblog.domain.Category;
import ua.org.jblog.domain.User;
import ua.org.jblog.dto.CreatePostDto;
import ua.org.jblog.dto.PostDto;
import ua.org.jblog.service.CategoryService;
import ua.org.jblog.service.PostService;
import ua.org.jblog.service.UserService;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
public class PostController
{
    private static final Logger LOG = LoggerFactory.getLogger(PostController.class);
    @Autowired
    private PostService postService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    @GetMapping("/post")
    public String getPageCreateAddPost(Model model, Principal principal)
    {
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categoriesToHTML", categories);
        model.addAttribute("principal", principal);
        return "createPost";
    }

    @PostMapping("/post")
    public String addPost(@ModelAttribute CreatePostDto newPost, Model model,
                          @RequestParam("file") MultipartFile file)
    {
        try
        {
            byte[] bytes = file.getBytes();
            User currentUser = userService.currentUser();
            postService.createPost(currentUser.getId(), newPost, bytes, file.getOriginalFilename());
            return "redirect:/";

        }
        catch (EmptyOrNullFieldException | IOException e)
        {
            LOG.error(e.getMessage(),e);
            List<Category> categories = categoryService.getAll();
            model.addAttribute("categoriesToHTML", categories);
            model.addAttribute("null_error", e.getMessage());

            return "createPost";
        }
    }

    @GetMapping("/post/{id}")
    public String getFullPost(Model model, @PathVariable int id)
    {
        PostDto post = postService.getPost(id);
        model.addAttribute("post", post);
        List<Category> categories = categoryService.getAll();
        model.addAttribute("categoriesToHTML", categories);
        return "fullpost";
    }

    @PostMapping("/post/{id}")
    public String createLike(@ModelAttribute PostDto postDto)
    {
        postService.sentenceLike(postDto);
        return "redirect:/post/{id}";
    }

    /*@GetMapping("/post/img/{fileName}")
    public ResponseEntity<byte[]> getImg(@PathVariable String fileName)
    {
        byte[] file = postService.getFile(fileName);
        return ResponseEntity
                .ok()
                .contentLength(file.length)
                .contentType(MediaType.IMAGE_JPEG)
                .cacheControl(CacheControl.maxAge(7, TimeUnit.DAYS))
                .body(file);
    }*/
}