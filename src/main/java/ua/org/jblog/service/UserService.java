package ua.org.jblog.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.org.jblog.Exception.InvalidFieldException;
import ua.org.jblog.domain.Role;
import ua.org.jblog.domain.User;
import ua.org.jblog.dto.UserRegDto;
import ua.org.jblog.repository.UserRepository;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registration(UserRegDto userRegDto)
    {
        String name = userRegDto.getName();
        if (StringUtils.isBlank(name))
        {
            throw new InvalidFieldException("Поле с ИМЕНЕМ не может быть пустым");
        }
        String password = userRegDto.getPassword();
        if (StringUtils.isBlank(password))
        {
            throw new InvalidFieldException("Поле с ПАРОЛЕМ не может быть пустым");
        }
        User user = new User();
        user.setName(userRegDto.getName());
        String encode = passwordEncoder.encode(userRegDto.getPassword());
        user.setPassword(encode);
        user.setRole(Role.USER);
        userRepository.save(user);
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
}
