package ua.org.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.org.jblog.Exception.InvalidEmailException;
import ua.org.jblog.Exception.InvalidPasswordException;
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
             model.addAttribute("name", userRegDto.getName());
             addCommonData(model);
             return "hello";
         }
         catch (InvalidEmailException exEmail)
         {
             model.addAttribute("error_req_email", exEmail.getMessage());
         }
         catch (InvalidPasswordException exPass)
         {
             model.addAttribute("error_req_pass", exPass.getMessage());
         }
         addCommonData(model);
         model.addAttribute("userRegDto", userRegDto);
         return "registration";
     }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code)
    {
        boolean isActivate = userService.activateUser(code);
        if (isActivate){
            model.addAttribute("activated","Дякуємо за підтвердженя! Ви успішно завершили реєстрацію.");
        }else {
            model.addAttribute("no_activated","Код активації не дійсний!");
        }
        addCommonData(model);
        return "login";
    }
}
