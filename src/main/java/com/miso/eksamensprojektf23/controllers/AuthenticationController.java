package com.miso.eksamensprojektf23.controllers;

import com.miso.eksamensprojektf23.models.Role;
import com.miso.eksamensprojektf23.models.User;
import com.miso.eksamensprojektf23.services.RoleService;
import com.miso.eksamensprojektf23.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
  public String updateUser(User user, RedirectAttributes redirectAttributes) {
    userService.updateUser(user);
    redirectAttributes.addFlashAttribute("message", "The user has been successfully updated in the database");
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
  public String saveUser(User user, RedirectAttributes redirectAttributes) {

    try {
      userService.saveDefaultUser(user);
      redirectAttributes.addFlashAttribute("message", "The user has been successfully saved in the database");
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("message", "A user with the same username already exists in the database.");
      return "redirect:/auth/user/register";
    }
    return "redirect:/auth/users";
  }

}
