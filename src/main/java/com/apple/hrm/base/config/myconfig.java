package com.apple.hrm.base.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.client.RestTemplate;

import com.apple.hrm.base.Service.impl.Userserviceimpl;

@Configuration
@EnableWebSecurity
public class myconfig {

	
	
	
	@Bean
	public  UserDetailsService getuserdetailservice() {
		return new Userserviceimpl();
	}
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationprovider() {
		DaoAuthenticationProvider auth=new DaoAuthenticationProvider();
		auth.setUserDetailsService(this.getuserdetailservice());
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
	
	
	
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authenticationprovider());
	}
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		 http.csrf(csrf->csrf.disable())
         .authorizeHttpRequests(auth->auth.requestMatchers("/user/**").authenticated().requestMatchers("/**").permitAll())
         .formLogin(login -> login
                 .loginPage("/login").loginProcessingUrl("/dologin").defaultSuccessUrl("/user/home")
                 .permitAll());
          return http.build();
		 }
	
	
	
	@Bean
	@LoadBalanced   // it is marker interfacce
	public RestTemplate resttemplet() {
		
		return new RestTemplate();
	}
	
	

	
	
	
}
