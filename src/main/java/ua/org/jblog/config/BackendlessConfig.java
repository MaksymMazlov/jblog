package ua.org.jblog.config;

import com.backendless.Backendless;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class BackendlessConfig
{
    @PostConstruct
    public void initBackendless(){
        Backendless.setUrl("https://eu-api.backendless.com");
        Backendless.initApp("67E35A86-6046-49F3-8931-6DF09C0DAEB3","9C95A6D0-A972-49FF-8FB8-96BFBBC21D8B");
    }
}
