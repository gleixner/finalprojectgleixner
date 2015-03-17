package com.expeditors.training.course3demo.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.expeditors.training.course3demo.config.AppConfig;
import com.expeditors.training.course3demo.model.Shipment;

public class StartSpring {

	public static void main(String... args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		
		context.register(AppConfig.class);
		
		Shipment s = new Shipment();
	}
	
}
