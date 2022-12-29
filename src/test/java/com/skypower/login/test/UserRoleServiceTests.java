package com.skypower.login.test;

import com.skypower.login.role.Role;
import com.skypower.login.role.UserRole;
import com.skypower.login.role.UserRoleService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder (MethodOrderer.OrderAnnotation.class)
public class UserRoleServiceTests {

   @Autowired
   private UserRoleService userRoleService;

   @Test
   @Order (1)
   public void userRoleFindAllTest() {
      List<UserRole> rolesList = userRoleService.findAll ();
      Assertions.assertThat (rolesList.size ()).isEqualTo (Role.values ().length);
   }

   @Test
   @Order (2)
   public void userRoleCountTest() {
      var rolesQuantity = userRoleService.count ();
      Assertions.assertThat (rolesQuantity).isEqualTo (Role.values ().length);
   }

   @Test
   @Order(3)
   public void userRoleFindByNameTest() {
      UserRole role = userRoleService.findByName (Role.ADMIN.name ());
      Assertions.assertThat (role.getRoleName ()).isEqualTo (Role.ADMIN.name ());
   }
}
