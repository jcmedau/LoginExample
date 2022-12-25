package com.skypower.login.user;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

   private UserRepository userRepository;
   private PasswordEncoder passwordEncoder;

   public UserService (UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
      this.userRepository = userRepository;
      this.passwordEncoder = passwordEncoder;
   }

   public Long count () {
      return userRepository.count ();
   }

   public void delete (Long id) {
      userRepository.deleteById (id);
   }

   public void encodePasswordAndSave (User user) {
      user.setPassword (passwordEncoder.encode (user.getPassword ()));
      saveOnly (user);
   }

   public Optional<User> findActiveUser (String username) {
      return userRepository.findActiveUser (username);
   }

   public List<User> findAll (String column) {
      if (column == null) column = "firstName";
      return userRepository.findAll (Sort.by (Direction.ASC, column));
   }

   public User findByEmail (String email) {
      return userRepository.findActiveUser (email)
            .orElseThrow (() -> new UsernameNotFoundException ("User not found " + email));
   }

   public User findById (Long id) {
      return userRepository
            .findById (id).orElseThrow (() -> new UsernameNotFoundException ("User not found " + id));
   }

   public void saveOnly (User user) {
      userRepository.save (user);
   }
}
