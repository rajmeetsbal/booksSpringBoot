package com.stackroute.favouriteservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*As in this class we are implementing Swagger So annotate the class with @Configuration and 
 * @EnableSwagger2
 * 
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.select()
                .apis(RequestHandlerSelectors.basePackage("com.stackroute.favouriteservice.controller"))
                .paths(PathSelectors.any()).build().pathMapping("/")
                .apiInfo(apiInfo()).useDefaultResponseMessages(false);
    }

    @Bean
    ApiInfo apiInfo() {
        ApiInfoBuilder builder = new ApiInfoBuilder();
        builder.title("FavoriteBooksServiceService API").version("1.0").license("(C) IBM").description("FavouriteBooks Service APIs");
        return builder.build();
    }
}