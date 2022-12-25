package com.skypower.login.user;

import com.skypower.login.role.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Copyright 2022 J. C. Medau All rights reserved.
 *
 * Class responsible for the User entity
 *
 * @author J. C. Medau
 * @version 1.0
 */

@Data
@Entity
@Table (name = "users")
public class User implements UserDetails, Serializable {

   @Serial
   private static final long serialVersionUID = 1L;

   @Id
   @Column (name = "user_id")
   @GeneratedValue (strategy = GenerationType.IDENTITY)
   private Long userId;

   @Column (length = 50)
   private String firstName;

   @Column (length = 50)
   private String lastName;

   @Column (length = 64)
   private String password;

   @Column (length = 50, unique = true, nullable = false)
   private String email;

   private Boolean isEnabled;

   @Temporal (TemporalType.DATE)
   private Date expiryDate;

   @ManyToMany (fetch = FetchType.EAGER)
   @JoinTable (name = "users_roles",
         joinColumns = @JoinColumn (name = "user_id"),
         inverseJoinColumns = @JoinColumn (name = "role_id"))
   private List<UserRole> roles;

   /*
    * This array is used during user update to maintain the assigned roles to the User.
    */
   @Transient
   private Boolean[] hasEachRole;

   public User () {
      this.roles = new ArrayList<UserRole> ();
   }

   public User (String firstName, String lastName, String password, String email,
                boolean isEnabled, UserRole role, Date expiryDate) {
      this ();
      this.firstName = firstName;
      this.lastName = lastName;
      this.password = password;
      this.email = email;
      this.isEnabled = isEnabled;
      addRole (role);
      this.expiryDate = expiryDate;
   }

   public boolean hasRole (UserRole role) {
      return roles.contains (role);
   }

   public void addRole (UserRole role) {
      roles.add (role);
   }

   /**
    * The regular expression [\[\]] removes the [ ] for a better view of the String
    */
   public String getAllRoles () {
      return roles.toString ().replaceAll ("[\\[\\]]", "");
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities () {
      return getRoles ();
   }

   @Override
   public String getUsername () {
      return email;
   }

   @Override
   public boolean isAccountNonExpired () {
      return ! Date.valueOf (LocalDate.now ()).after (getExpiryDate ());
   }

   @Override
   public boolean isAccountNonLocked () {
      return isEnabled ();
   }

   @Override
   public boolean isCredentialsNonExpired () {
      return isAccountNonExpired ();
   }

   @Override
   public boolean isEnabled () {
      return isEnabled;
   }
}