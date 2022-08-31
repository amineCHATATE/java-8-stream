package com.amine.javastreams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Amine Chatate
 * @version 1.0
 * @date 07/03/2022 15:10
 * @description
 */
@EnableWebMvc
@SpringBootApplication
public class JavaStreamsApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaStreamsApplication.class, args);
    }

}
