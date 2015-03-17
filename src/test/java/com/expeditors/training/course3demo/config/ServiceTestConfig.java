package com.expeditors.training.course3demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@Import({PersistenceJPATestConfig.class})
@ComponentScan(basePackages={"com.expeditors.training.course3demo.service", 
		"com.expeditors.training.course3demo.dao",
		"com.expeditors.training.course3demo.factories"})
public class ServiceTestConfig {

}


//package com.expeditors.training.course3demo.config;
//
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import com.expeditors.training.course3demo.config.PersistenceJPATestConfig;
//
//@Configuration
//@ComponentScan(basePackages = "com.expeditors.training.course3demo.service")
//@Import({PersistenceJPATestConfig.class})
//public class ServiceTestConfig {
//
//}
