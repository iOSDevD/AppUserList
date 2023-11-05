package com.userlist.home;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebMVCConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureContentNegotiation(
			ContentNegotiationConfigurer configurer) {
	    configurer.favorPathExtension(true).
        favorParameter(false).
        ignoreAcceptHeader(true).
        defaultContentType(MediaType.APPLICATION_JSON);		
	}
	
}
