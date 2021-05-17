package ua.org.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.org.jblog.dto.CaptchaResponseDto;

import java.util.Collections;

@Service
public class RecaptchaService
{
    private static final String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";
    @Autowired
    private RestTemplate restTemplate;
    @Value("${app.recaptcha.secret}")
    private String secret;

    public boolean validate(String response)
    {
        String url = String.format(CAPTCHA_URL, secret, response);
        CaptchaResponseDto fullResponse = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);
        return fullResponse.isSuccess();
    }
}
