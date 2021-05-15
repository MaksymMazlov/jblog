package ua.org.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.org.jblog.Exception.InvalidFieldException;
import ua.org.jblog.dto.UserRegDto;
import ua.org.jblog.service.UserService;

@Controller
public class UserRegController extends AbstractPageController
{
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String showPageReg(Model model)
    {
        addCommonData(model);
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute UserRegDto userRegDto, Model model)
    {
        try
        {
            userService.registration(userRegDto);
            model.addAttribute("name",userRegDto.getName());
            addCommonData(model);
            return "hello";
        }
        catch (InvalidFieldException e){
            addCommonData(model);
            model.addAttribute("error_req", e.getMessage());
            return "registration";
        }
    }

}
