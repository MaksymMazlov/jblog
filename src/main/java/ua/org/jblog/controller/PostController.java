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
import ua.org.jblog.dto.CommentDto;
import ua.org.jblog.dto.CreatePostDto;
import ua.org.jblog.dto.PostDto;
import ua.org.jblog.service.CategoryService;
import ua.org.jblog.service.CommentService;
import ua.org.jblog.service.LikeCommentService;
import ua.org.jblog.service.LikePostService;
import ua.org.jblog.service.PostService;
import ua.org.jblog.service.UserService;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Set;

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
    @Autowired
    private CommentService commentService;
    @Autowired
    private LikeCommentService likeCommentService;
    @Autowired
    private LikePostService likePostService;
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
            LOG.error(e.getMessage(), e);
            List<Category> categories = categoryService.getAll();
            model.addAttribute("categoriesToHTML", categories);
            model.addAttribute("null_error", e.getMessage());

            return "createPost";
        }
    }

    @GetMapping("/post/{id}")
    public String getFullPost(Model model, @PathVariable int id, Principal principal)
    {
        PostDto post = postService.getPost(id);
        model.addAttribute("post", post);

        List<Category> categories = categoryService.getAll();
        model.addAttribute("categoriesToHTML", categories);

        List<CommentDto> commentList = commentService.getAllComment(id);
        model.addAttribute("comments_list", commentList);

        model.addAttribute("isLike", likePostService.likeExist(id));
        Set<Integer> likedCommentsByUser = likeCommentService.getLikedCommentsByUser(userService.currentUser().getId(), id);
        model.addAttribute("likedComments", likedCommentsByUser);
        model.addAttribute("principal", principal);
        return "fullpost";

    }

    @PostMapping("/post/{id}")
    public String createLike(@ModelAttribute PostDto postDto)
    {
        likePostService.createLike(postDto);
        return "redirect:/post/{id}";
    }

    @PostMapping("/post/{postId}/comments/{commentId}")
    public String createLikeCom(@PathVariable("commentId") int commentId,
                                @PathVariable("postId") int postId,
                                int likes)
    {
        likeCommentService.sentenceLikeCom(commentId, likes);
        return "redirect:/post/{postId}";
    }

    @PostMapping("/post/{id}/comments")
    public String createComment(@PathVariable("id") int idPost, String comment)
    {
        try
        {
            commentService.createComment(comment, idPost);
            return "redirect:/post/{id}";
        }
        catch (EmptyOrNullFieldException e)
        {
            return "redirect:/post/{id}";
        }
    }

    @GetMapping("/post/{postId}/comments/{commentId}/del")
    public String deleteComment(@PathVariable("commentId") int commentId)
    {
        commentService.delComment(commentId);
        return "redirect:/post/{postId}";
    }

    @PostMapping("/post/{postId}/comments/{commentId}/edit")
    public String editComment(@PathVariable("commentId") int commentId,
                              @PathVariable("postId") int postId,
                              String comment)
    {
        try
        {
            commentService.updateComment(comment, commentId);
            return "redirect:/post/{postId}";
        }
        catch (EmptyOrNullFieldException e)
        {
            return "redirect:/post/{postId}";
        }
    }
}