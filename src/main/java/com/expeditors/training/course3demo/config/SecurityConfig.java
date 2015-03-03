package com.expeditors.training.course3demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService userDetailsServiceImpl;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
//		auth.inMemoryAuthentication().withUser("abc").password("123456").roles("USER", "CONTENT", "MAINT", "CUSTOMER");
//		auth.inMemoryAuthentication().withUser("con").password("asdf").roles("CONTENT");
//		auth.inMemoryAuthentication().withUser("maint").password("work").roles("MAINT");
//		auth.inMemoryAuthentication().withUser("cust").password("god").roles("CUSTOMER");
		auth.userDetailsService( userDetailsServiceImpl );
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/resources/**").permitAll()
		.antMatchers("/login/**").permitAll()
		.antMatchers("/product/*").hasRole("CONTENT")
		.antMatchers("/card/*").hasRole("USER")
		.antMatchers("/container/*").hasRole("MAINT")
		.antMatchers("/shipment/*").hasRole("CUSTOMER")
		.antMatchers("/security/*").permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
			.loginPage("/login").permitAll()
		.and()
//		.logout().permitAll()
//		.and()
		.httpBasic();
	}
	
}
