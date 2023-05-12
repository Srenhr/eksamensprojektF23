package com.miso.eksamensprojektf23.controllers;

import com.miso.eksamensprojektf23.models.Role;
import com.miso.eksamensprojektf23.models.User;
import com.miso.eksamensprojektf23.services.RoleService;
import com.miso.eksamensprojektf23.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/admin/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final UserService userService;
  private final RoleService roleService;

  @GetMapping("/users")
  public String listUsers(Model model) {
    List<User> listUsers = userService.getAllUsers();
    model.addAttribute("listUsers", listUsers);
    return "users";
  }

  @GetMapping("/user/edit/{id}")
  public String editUser(@PathVariable("id") Long id, Model model) {
    User user = userService.getUserById(id);
    List<Role> listRoles = roleService.getAllRoles();
    model.addAttribute("user", user);
    model.addAttribute("listRoles", listRoles);
    return "user_edit_form";
  }

  @PostMapping("/user/update")
  public String updateUser(User user) {
    System.out.println(user);
    userService.updateUser(user);
    return "redirect:/admin/auth/users";
  }

}
