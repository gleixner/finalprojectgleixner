package com.expeditors.training.course3demo.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
//import org.springframework.context.annotation.FilterType;
//import org.springframework.context.annotation.ComponentScan.Filter;


@EnableWebMvc
@Configuration
@ComponentScan(basePackages={"com.expeditors.training.course3demo", "edu.uw.course3demo.rest"}
//			   ,excludeFilters = @Filter( type=FilterType.ASSIGNABLE_TYPE, value={PersistenceJPATestConfig.class})
			  )
@Import( {SecurityConfig.class, PersistenceJPAConfig.class} )
public class AppConfig extends WebMvcConfigurerAdapter {
	
	@Autowired 
	EntityManagerFactory emf;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addWebRequestInterceptor( openEntityManagerInView() );
	}
	
	@Bean
	public OpenEntityManagerInViewInterceptor openEntityManagerInView() {
		OpenEntityManagerInViewInterceptor i = new OpenEntityManagerInViewInterceptor();
		i.setEntityManagerFactory(emf);
		return i;
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
		resource.setBasename("messages");
		return resource;
	}
	
	@Override
	public void addResourceHandlers( final ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("resources/**")
			.addResourceLocations("/resources/");
	}
	
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

}
