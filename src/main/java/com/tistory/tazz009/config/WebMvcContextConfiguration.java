package com.tistory.tazz009.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.tistory.tazz009.converter.StringToEntityConverter;
import com.tistory.tazz009.domain.Category;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.tistory.tazz009" })
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**/*").addResourceLocations(
				"classpath:/META-INF/web-resources/");
	}

	@Override
	public void addViewControllers(final ViewControllerRegistry registry) {
		registry.addViewController("/index.htm").setViewName("index");
	}

	@Override
    public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    public HandlerInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }
    
    @Bean
    public LocaleResolver localeResolver() {
        return new CookieLocaleResolver();
    }
    
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/messages");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }
    
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(categoryConverter());
        registry.addFormatter(new DateFormatter("dd-MM-yyyy"));
    }

    @Bean
    public StringToEntityConverter categoryConverter() {
        return new StringToEntityConverter(Category.class);
    }
}
