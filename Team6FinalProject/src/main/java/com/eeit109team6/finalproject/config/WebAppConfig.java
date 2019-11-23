package com.eeit109team6.finalproject.config;

import java.util.ArrayList;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;


@Configuration
@EnableWebMvc
@ComponentScan({ "com.eeit109team6.finalproject.controller", "com.eeit109team6.finalproject.config",
		"com.eeit109team6.finalproject.dao.impl", "com.eeit109team6.finalproject.service.impl"

})
//public class WebAppConfig extends WebMvcConfigurerAdapter {   old
@Component
public class WebAppConfig implements WebMvcConfigurer { // new

	@Bean
	public ViewResolver Resolver() {
		System.out.println("WebAppConfig : WebMvcConfigurerAdapter : ViewResolver");
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor());

	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
		resource.setBasename("messages");
		return resource;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/CSS/**").addResourceLocations("/WEB-INF/views/CSS/");
		registry.addResourceHandler("/JS/**").addResourceLocations("/WEB-INF/views/JS/");
		registry.addResourceHandler("/Images/**").addResourceLocations("/WEB-INF/views/Images/");
		registry.addResourceHandler("/icon-fonts/**").addResourceLocations("/WEB-INF/views/icon-fonts/");
		registry.addResourceHandler("/Movie/**").addResourceLocations("/WEB-INF/views/Movie/");
		registry.addResourceHandler("/memberImages/**").addResourceLocations("file:C:/memberImages/");
		registry.addResourceHandler("/memberMovies/**").addResourceLocations("file:C:/memberMovies/");

	}

//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**");
//	}

//	CommonsMultipartResolver               ***********************************************
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("UTF-8");
		resolver.setMaxUploadSize(81920000);
		return resolver;
	}
//	CommonsMultipartResolver               ***********************************************
	
	@Bean
	public MappingJackson2JsonView jsonview() {
		MappingJackson2JsonView view = new MappingJackson2JsonView();
		view.setPrettyPrint(true);
		return view;
	}

	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);
		ArrayList<View> views = new ArrayList<>();
		views.add(jsonview());
		resolver.setDefaultViews(views);
		return resolver;

	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.defaultContentType(MediaType.APPLICATION_JSON);
	}
}
