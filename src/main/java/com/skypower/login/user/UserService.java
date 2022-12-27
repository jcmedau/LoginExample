package com.skypower.login.user;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright 2022 J. C. Medau All rights reserved.
 *
 * Service for the User Entity
 *
 * @author J. C. Medau
 * @version 1.0
 */

@Service
public class UserService implements UserDetailsService {

   private UserRepository userRepository;
   private PasswordEncoder passwordEncoder;

   public UserService (UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
      this.userRepository = userRepository;
      this.passwordEncoder = passwordEncoder;
   }

   public void delete (Long id) {
      userRepository.deleteById (id);
   }

   public void encodePasswordAndSave (User user) {
      user.setPassword (passwordEncoder.encode (user.getPassword ()));
      save (user);
   }

   public List<User> findAll (String column) {
      if (column == null) column = "firstName";
      return userRepository.findAll (Sort.by (Direction.ASC, column));
   }

   public User findById (Long id) {
      return userRepository
            .findById (id).orElseThrow (
                  () -> new UsernameNotFoundException ("User not found " + id));
   }

   @Override
   public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
      return userRepository.findActiveUser(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
   }

   public void save (User user) {
      userRepository.save (user);
   }
}
