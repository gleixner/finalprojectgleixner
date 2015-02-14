package com.expeditors.training.course3demo.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.expeditors.training.course3demo.service.ContainerService;
import com.expeditors.training.course3demo.service.ProductService;
import com.expeditors.training.course3demo.service.ShipmentService;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages="com.expeditors.training.course3demo.controller")
public class TestConfig {

	@Bean
	public ProductService productService() {
		return Mockito.mock(ProductService.class);
	}
	
	@Bean
	public ContainerService containerService() {
		return Mockito.mock(ContainerService.class);
	}
	
	@Bean
	public ShipmentService shipmentService() {
		return Mockito.mock(ShipmentService.class);
	}
	
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewer = new InternalResourceViewResolver();
		viewer.setPrefix("/WEB-INF/view/");
		viewer.setSuffix(".jsp");
		viewer.setViewClass(JstlView.class);
		return viewer;
	}
	
}
