package ua.org.jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginPageController extends AbstractPageController
{
    @GetMapping("/login")
    public String login(Model model)
    {
        addCommonData(model);
        return "login";
    }
}
