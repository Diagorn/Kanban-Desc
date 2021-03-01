package com.rosatom.kanban.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private Environment env;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) { //Configuring mapping for Spring Security
        registry.addViewController("/login").setViewName("login"); //Setting the login page
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (env.getProperty("spring.profiles.active") != null
                && env.getProperty("staticfiles.path") != null
                && env.getProperty("spring.profiles.active").equals("local")) {
            registry.addResourceHandler("/static/**") //Static files applyment (such as CSS or JS)
                    .addResourceLocations("classpath:/static/");
        } else {
            registry.addResourceHandler("/static/**") //Static files applyment (such as CSS or JS)
                    .addResourceLocations("file:" + env.getProperty("staticfiles.path"));
        }
        registry.addResourceHandler("/table/**")
                .addResourceLocations("classpath:/static/");
    }
}