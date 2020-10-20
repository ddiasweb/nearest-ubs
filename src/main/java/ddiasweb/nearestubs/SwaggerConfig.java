package com.nearestubs.nearestubs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nearestubs.nearestubs.util.Version;

import io.swagger.annotations.SwaggerDefinition;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@SwaggerDefinition
@EnableSwagger2
public class SwaggerConfig extends SpringBootServletInitializer {

	@Autowired
	Version version;

	@Bean
    public Docket produceApi(){
    return new Docket(DocumentationType.SWAGGER_2)
	    .apiInfo(apiInfo())
	    .select()
	    .apis(RequestHandlerSelectors.basePackage("com.nearestubs.nearestubs.controller"))
	    .paths(PathSelectors.ant("/**"))
	    .build();
	}

	private ApiInfo apiInfo() {
	    return new ApiInfoBuilder()
		    .title("Nearest UBS Rest APIs")
		    .description("Locate nearest UBS Web API.")
		    .version(version.getVersion())
		    .build();
	}
}