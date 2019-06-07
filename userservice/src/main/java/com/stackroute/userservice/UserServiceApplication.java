package com.stackroute.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import brave.sampler.Sampler;

@SuppressWarnings("deprecation")
@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {

	@Bean
    public WebMvcConfigurer corsConfigurer() {
		WebMvcConfigurer webConfigurer = new WebMvcConfigurerAdapter() {
			@Override
            public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/v1").allowedOrigins("http://localhost:4200", "https://localhost:4200");
            }
		};
        return webConfigurer;
    }
	
	@Bean
	public Sampler defaultSampler(){
		return Sampler.ALWAYS_SAMPLE;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
}



