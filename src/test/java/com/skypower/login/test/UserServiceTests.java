package com.skypower.login.test;

import com.skypower.login.role.UserRoleService;
import com.skypower.login.user.User;
import com.skypower.login.user.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@SpringBootTest
@TestMethodOrder (MethodOrderer.OrderAnnotation.class)
public class UserServiceTests {

   @Autowired
   private UserService userService;

   @Autowired
   private UserRoleService userRoleService;

   @Test
   @Order (1)
   public void findUserByIdTest() {
      var givenId = 1l;
      User user = userService.findById (givenId);
      Assertions.assertThat (user.getUserId ()).isEqualTo (givenId);
   }

   @Test
   @Order (2)
   public void findAllUsersTest() {
      List<User> allUsers = userService.findAll ("firstName");
      Assertions.assertThat (allUsers.size ()).isEqualTo (1);
   }

   @Test
   @Order(3)
   public void loadUserByUsernameTest() {
      UserDetails details = userService.loadUserByUsername ("sudo");
      Assertions.assertThat (details.getUsername ()).isEqualTo ("sudo");
   }

   @Test
   @Order(4)
   public void saveUserTest() {
      User newUser = new User("Test",
            "Test User",
            "123",
            "TestName",
            true,
            userRoleService.findByName ("USER"));
      userService.save (newUser);
      Assertions.assertThat (userService.findAll (null).size ()).isEqualTo (2);
   }

   @Test
   @Order(5)
   public void deleteUserTest() {
      userService.delete (2l);
      Assertions.assertThat (userService.findAll (null).size ()).isEqualTo (1);
   }
}
