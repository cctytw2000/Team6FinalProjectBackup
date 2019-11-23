package com.eeit109team6.finalproject.config;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
		implements WebApplicationInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {

		return new Class[] { RootAppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebAppConfig.class, LoginInterceptor.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return new Filter[] { characterEncodingFilter };
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(WebAppConfig.class);
		rootContext.setServletContext(servletContext);
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher",
				new DispatcherServlet(rootContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		FilterRegistration.Dynamic filterRegistration = servletContext.addFilter("endcodingFilter",
				new CharacterEncodingFilter());
		filterRegistration.setInitParameter("encoding", "UTF-8");
		filterRegistration.setInitParameter("forceEncoding", "true");
		// make sure encodingFilter is matched first
		filterRegistration.addMappingForUrlPatterns(null, false, "/*");
		filterRegistration = servletContext.addFilter("OpenSessionInViewFilter", OpenSessionInViewFilter.class);
		filterRegistration.setInitParameter("singleSession", "true");
		filterRegistration.addMappingForServletNames(null, true, "dispatcher");

		/**
		 * Add Spring ContextLoaderListener
		 */
		servletContext.addListener(new ContextLoaderListener(rootContext));
		
		//用於  MovieController的"/moviepersonal/uploadFile"                             Start
		// FilterRegistration.Dynamic multipartFilter = servletContext.addFilter("multipartFilter", MultipartFilter.class);
		// multipartFilter.addMappingForUrlPatterns(null, true, "/*");
		//用於  MovieController的"/moviepersonal/uploadFile"                             End
	}
	
//	@Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//
//        AnnotationConfigWebApplicationContext dispatcherServletContext = new AnnotationConfigWebApplicationContext();
//
//        dispatcherServletContext.register(SpringConfig.class);
//
//        DispatcherServlet dispatcherServlet = new DispatcherServlet(dispatcherServletContext);
//
//        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("file-upload", dispatcherServlet);
//         
//        /* If do not set multipartconfig in servlet 3, when you upload a file, it will throw Unable to process parts as no multi-part configuration has been provided error. */
//        MultipartConfigElement multipartConfig = new MultipartConfigElement("/tmp");
//        dispatcher.setMultipartConfig(multipartConfig);
//
//        dispatcher.setLoadOnStartup(1);
//
//        dispatcher.addMapping("*.html");
//
//        FilterRegistration.Dynamic multipartFilter = servletContext.addFilter("multipartFilter", MultipartFilter.class);
//
//        multipartFilter.addMappingForUrlPatterns(null, true, "/*");
//    }

}
