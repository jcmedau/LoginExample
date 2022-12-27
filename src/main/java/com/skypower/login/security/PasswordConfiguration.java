package com.skypower.login.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Copyright 2022 J. C. Medau All rights reserved.
 *
 * Configuration Class responsible for password encryption
 *
 * @author J. C. Medau
 * @version 1.0
 */
@Configuration
public class PasswordConfiguration {

   @Bean
   PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder ();
//    	return NoOpPasswordEncoder.getInstance();	// switch lines to use non-encrypted passwords in the database
   }
}
