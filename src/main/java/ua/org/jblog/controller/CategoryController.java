package ua.org.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.org.jblog.domain.Category;
import ua.org.jblog.domain.Role;
import ua.org.jblog.service.CategoryService;
import ua.org.jblog.service.UserService;

@Controller
public class CategoryController extends AbstractPageController
{
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    @Value("${app.category.create.admin-only:true}")
    private boolean adminOnly;

    @GetMapping("/category")
    public String createCategoryPage(Model model)
    {
        if (userService.currentUser() == null || (adminOnly && userService.currentUser().getRole() != Role.ADMIN))
        {
            model.addAttribute("error", "Access denied");
        }
        addCommonData(model);
        return "category";
    }

    @PostMapping("/category")
    public String addCategory(@ModelAttribute Category category, Model model)
    {
        if (userService.currentUser() == null || (adminOnly && userService.currentUser().getRole() != Role.ADMIN))
        {
            model.addAttribute("error", "Access denied");
        }
        else
        {
            categoryService.save(category);
        }
        addCommonData(model);

        return "category";
    }


}
