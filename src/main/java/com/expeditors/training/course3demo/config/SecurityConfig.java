package com.expeditors.training.course3demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;



@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("abc").password("123456").roles("USER", "CONTENT", "MAINT", "CUSTOMER");
		auth.inMemoryAuthentication().withUser("con").password("asdf").roles("CONTENT");
		auth.inMemoryAuthentication().withUser("maint").password("work").roles("MAINT", "CUSTOMER");
		auth.inMemoryAuthentication().withUser("cust").password("god").roles("CUSTOMER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
//		.antMatchers("/static/**").permitAll()
		.antMatchers("/product/*").hasRole("CONTENT")
		.antMatchers("/card/*").hasRole("USER")
		.antMatchers("/container/*").hasRole("MAINT")
		.antMatchers("/shipment/*").hasRole("CUSTOMER")
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.and()
		.httpBasic();
	}
	
}
