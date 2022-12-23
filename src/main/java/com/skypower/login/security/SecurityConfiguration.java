package com.skypower.login.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.skypower.login.user.CustomUserDetailsService;

/**
 * Copyright 2022 J. C. Medau All rights reserved.
 *
 * Configuration Class responsible for login, logout, and authorizations
 *
 * @author J. C. Medau
 * @version 1.0
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity	// this line is required to enable @PreAuthorize on the Controller methods 
public class SecurityConfiguration {
			
	private final CustomUserDetailsService userDetailsServiceImpl;
	
	public SecurityConfiguration (CustomUserDetailsService userDetailsServiceImpl) {
		this.userDetailsServiceImpl = userDetailsServiceImpl;
	}
		
	/**
	 * This Bean configures login with a custom form, logout with a custom page and free access to
	 * the home page index.html
	 *
	 */
	
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
    	return http
    			.authorizeHttpRequests(auth -> {
    				auth.requestMatchers("/", "/logout", "/end", "/css/**").permitAll();   
    				auth.anyRequest().authenticated();
    			})    		
    			.formLogin()
					.loginPage("/login")
					.permitAll()			
				.and()
				.formLogin(Customizer.withDefaults()) 
    			    			
    			.logout() 
    		    	.clearAuthentication(true)
    		    	.logoutRequestMatcher(new AntPathRequestMatcher("/end")) 
    		        .logoutSuccessUrl("/logout") 
    		        .deleteCookies("JSESSIONID")
    		        .invalidateHttpSession(true)
    			.and()   
    				.userDetailsService(userDetailsServiceImpl)
    				.httpBasic(Customizer.withDefaults())    			
    			.build();
    }
    
    /**
     * This PasswordEncoder is used to login and also to encrypt the password before saving the user to the database.
     * The UserService class uses it.
     */
       
    @Bean
    PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
//    	return NoOpPasswordEncoder.getInstance();	// switch lines to use non-encrypted passwords in the database
    }
}

