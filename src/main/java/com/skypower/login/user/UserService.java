package com.skypower.login.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Copyright 2022 J. C. Medau All rights reserved.
 *
 * Service for the User Entity
 *
 * @author J. C. Medau
 * @version 1.0
 */

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
		
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * @return the number of Users in the database
	 */
	public Long count() {
		return userRepository.count();
	}	
	
	/**
	 * Deletes a User from the database
	 * @param the Id of the user to be deleted
	 */
	public void delete (Long id) {
		userRepository.deleteById(id);
	}
	
	/**
	 * Encodes the User password using the @Bean from the <code>SecurityConfiguration</code> Class and save it.
	 * @param the User to be saved
	 */
	public void encodePasswordAndSave (User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		saveOnly(user);
	}
	
	/**
	 * @return a List with all Users in the database sorted the the given attribute. If no attribute is given, the
	 * default is the first name.
	 * @param the attribute to be used for sorting the List.
	 */
	public List<User> findAll(String column) {
		if (column == null) column = "firstName";
		return userRepository.findAll(Sort.by(Direction.ASC, column));
	}
	
	/**
	 * @return a User found by a given email. Throws a UsernameNotFoundException if the User does not exist.
	 * @param the email to be searched.
	 */	
	public User findByEmail (String email) {
		return userRepository.findActiveUser(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found " + email));
	}	
	
	/**
	 * @return a User found by a given id. Throws a UsernameNotFoundException if the User does not exist.
	 * @param the id to be searched.
	 */
	public User findById (Long id) {
		return userRepository
				.findById(id).orElseThrow(() -> new UsernameNotFoundException ("User not found " + id));

	}
	
	/**
	 * Saves a User to the database without encrypting its password.
	 * @param User to be saved.
	 */
	public void saveOnly (User user) {
		userRepository.save(user);
	}
}
