package ua.org.jblog.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.org.jblog.Exception.InvalidEmailException;
import ua.org.jblog.Exception.InvalidPasswordException;
import ua.org.jblog.domain.Role;
import ua.org.jblog.domain.User;
import ua.org.jblog.dto.UserRegDto;
import ua.org.jblog.repository.UserRepository;

import java.util.UUID;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MailSender mailSender;


    public void registration(UserRegDto userRegDto)
    {
        String name = userRegDto.getName();
        if (StringUtils.isBlank(name))
        {
            throw new InvalidEmailException("*Невірний e-mail - поле не може бути пустим");
        }
        if (!name.contains("@") || (name.length() < 3))
        {
            throw new InvalidEmailException("*Невірний e-mail - введіть будь ласка корректний e-mail");

        }

        String password = userRegDto.getPassword();

        if (StringUtils.isBlank(password))
        {
            throw new InvalidPasswordException("*Невірний пароль - поле не може бути пустим");
        }
        if (password.length() < 6 || password.length() > 24)
        {
            throw new InvalidPasswordException("*Невірний пароль - пароль повинен бути від 6 до 24 символів");

        }


        User userFromDb = userRepository.findByName(name);
        if (userFromDb != null)
        {
            throw new InvalidEmailException("*Невірний e-mail - користувач з таким e-mail вже зареєстрований!");
        }

        User user = new User();
        user.setName(userRegDto.getName());
        String encode = passwordEncoder.encode(userRegDto.getPassword());
        user.setPassword(encode);
        user.setRole(Role.USER);
        user.setCode(UUID.randomUUID().toString());
        userRepository.save(user);

        String link = "http://localhost:8080/activate/%s";
        String message = String.format("Дякуємо за реєстрацію!\n" +
                        "Для підтвердження перейдіть за посиланням: " + link,
                user.getCode());
        mailSender.sendEmail(user.getName(), "Activation code", message);
    }

    public User currentUser()
    {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext == null)
        {
            return null;
        }
        Authentication authentication = securityContext.getAuthentication();
        if (authentication == null)
        {
            return null;
        }

        String authenticationName = authentication.getName();
        if ("anonymousUser".equals(authenticationName))
        {
            return null;
        }
        return userRepository.findByName(authenticationName);
    }

    public boolean activateUser(String code)
    {
        User user = userRepository.findByCode(code);
        if (user == null)
        {
            return false;
        }
        user.setCode(null);
        user.setActive(true);
        userRepository.save(user);
        return true;
    }
}
