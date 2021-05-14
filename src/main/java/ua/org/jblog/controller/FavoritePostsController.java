package ua.org.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.org.jblog.service.FavoritePostService;

@Controller
public class FavoritePostsController extends AbstractPageController
{
    @Autowired
    private FavoritePostService favoritePostService;

    @GetMapping("/favorites")
    public String getFav(Model model)
    {
        addCommonData(model);
        model.addAttribute("fav_posts_list", favoritePostService.getAllFavPost());
        return "favoritePosts";
    }
}
