package com.tistory.tazz009.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class BookstoreWebApplicationInitializer implements
		WebApplicationInitializer {

	private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

	@Override
    public void onStartup(final ServletContext servletContext) throws ServletException {
		registerListener(servletContext);
        registerDispatcherServlet(servletContext);
    }

    private void registerListener(ServletContext servletContext) {
    	AnnotationConfigWebApplicationContext rootContext;
        rootContext = createContext(InfrastructureContextConfiguration.class, TestDataContextConfiguration.class);
        servletContext.addListener(new ContextLoaderListener(rootContext));
	}

	private void registerDispatcherServlet(final ServletContext servletContext) {
		WebApplicationContext dispatcherContext = createContext(WebMvcContextConfiguration.class);
        ServletRegistration.Dynamic dispatcher;
        dispatcher = servletContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }

	private AnnotationConfigWebApplicationContext createContext(final Class<?>... annotatedClasses) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(annotatedClasses);
        return context;
    }
}
