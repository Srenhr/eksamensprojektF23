package com.miso.eksamensprojektf23.controllers;

import com.miso.eksamensprojektf23.models.Role;
import com.miso.eksamensprojektf23.models.User;
import com.miso.eksamensprojektf23.services.RoleService;
import com.miso.eksamensprojektf23.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final UserService userService;
  private final RoleService roleService;

  @GetMapping("/users")
  public String listUsers(Model model) {
    List<User> users = userService.getAllUsers();
    // Sort the list by the id field
    users.sort(Comparator.comparingLong(User::getUserId));
    model.addAttribute("users", users);
    return "users";
  }

  @GetMapping("/user/edit/{id}")
  public String editUser(@PathVariable("id") Long id, Model model) {
    User user = userService.getUserById(id);
    List<Role> roles = roleService.getAllRoles();
    // Sort the list by the id field
    roles.sort(Comparator.comparingLong(Role::getRoleId));
    model.addAttribute("user", user);
    model.addAttribute("roles", roles);
    return "user_edit_form";
  }

  @PostMapping("/user/update")
  public String updateUser(User user) {
    userService.updateUser(user);
    return "redirect:/auth/users";
  }

  @GetMapping("/user/register")
  public String registerUser(Model model) {
    User user = new User();
    List<Role> roles = roleService.getAllRoles();
    // Sort the list by the id field
    roles.sort(Comparator.comparingLong(Role::getRoleId));
    model.addAttribute("user", user);
    model.addAttribute("roles", roles);
    return "user_register_form";
  }

  @PostMapping("/user/save")
  public String saveUser(User user) {
    userService.saveDefaultUser(user);
    return "redirect:/auth/users";
  }

}
