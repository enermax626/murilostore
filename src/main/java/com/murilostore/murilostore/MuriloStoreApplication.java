package com.murilostore.murilostore;

import java.util.Objects;
import java.util.Set;

import com.murilostore.murilostore.config.ProfileConfig;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@Slf4j
public class MuriloStoreApplication {

	public static void main(String[] args) {
		log.info("BEFORE STARTING PROGRAM");
		SpringApplication.run(MuriloStoreApplication.class, args);

	}

}
