package com.murilostore.murilostore.config;

import java.util.Objects;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.web.WebApplicationInitializer;

@Configuration
public class ProfileConfig{

    public static final String PROD = "prod";

    public static final String DEV = "dev";

    public static final String TEST = "test";

    public static void configureProfile(){
//        if(Objects.isNull(System.getProperty("spring.profiles.active"))) {
//            System.setProperty("spring.profiles.active", DEV);
//        }
    }
}
