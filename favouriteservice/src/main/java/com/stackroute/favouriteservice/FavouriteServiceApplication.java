package com.stackroute.favouriteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.stackroute.favouriteservice.jwttoken.JwtFilter;

@SuppressWarnings("deprecation")
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableDiscoveryClient
public class FavouriteServiceApplication {

	@Bean
	public FilterRegistrationBean jwtFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/api/v1/*");
		return registrationBean;
	}

	@SuppressWarnings("deprecation")
	@Bean
    public WebMvcConfigurer corsConfigurer() {
		WebMvcConfigurer webConfigurer = new WebMvcConfigurerAdapter() {
			@Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/v1").allowedOrigins("http://localhost:4200/*", "https://localhost:4200/*");
            }
		};
		return webConfigurer;
    }
	
	public static void main(String[] args) {
		SpringApplication.run(FavouriteServiceApplication.class, args);
	}

}

