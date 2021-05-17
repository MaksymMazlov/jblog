package ua.org.jblog.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import ua.org.jblog.service.RecaptchaService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class UserLoginRecaptchaFilter extends GenericFilterBean
{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginRecaptchaFilter.class);
    @Autowired
    private RecaptchaService recaptchaService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if ("/login".equals(request.getServletPath()) && "POST".equals(request.getMethod()))
        {
            LOGGER.info("IN UserLoginRecaptchaFilter");
            String responseCaptcha = request.getParameter("g-recaptcha-response");
            if (recaptchaService.validate(responseCaptcha))
            {
                filterChain.doFilter(servletRequest, servletResponse);
            }else {
                ((HttpServletResponse)servletResponse).sendRedirect("/login?error");
            }
        }
        else
        {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
