package com.skypower.login;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
	
	private final UserDetailsServiceImpl userDetailsServiceImpl;
	
	public SecurityConfiguration (UserDetailsServiceImpl userDetailsServiceImpl) {
		this.userDetailsServiceImpl = userDetailsServiceImpl;
	}
		
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
    	return http
    			.authorizeHttpRequests(auth -> {
    				auth.requestMatchers("/").permitAll();    	
//    				auth.requestMatchers("/login.html").permitAll(); 
//    				auth.requestMatchers("/admin/**").hasRole("ADMIN");
//    				auth.requestMatchers("/user/**").hasRole("USER");
    				auth.anyRequest().authenticated();
    			})
    			.formLogin(Customizer.withDefaults())    			
    			.userDetailsService(userDetailsServiceImpl)
    			.httpBasic(Customizer.withDefaults())
    			.build();
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
//    	return NoOpPasswordEncoder.getInstance();
    }
}

