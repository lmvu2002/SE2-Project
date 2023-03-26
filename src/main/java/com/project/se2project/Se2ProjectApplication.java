package com.project.se2project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@Configuration
@EnableWebMvc
public class Se2ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Se2ProjectApplication.class, args);
    }

    //nếu có nhiều controller, dùng cái này
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/").allowedOrigins("http://localhost:3000");
//            }
//        };
//    }
}
