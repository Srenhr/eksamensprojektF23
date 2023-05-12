package com.miso.eksamensprojektf23.auth;

import com.miso.eksamensprojektf23.models.Role;
import com.miso.eksamensprojektf23.models.User;
import com.miso.eksamensprojektf23.services.RoleService;
import com.miso.eksamensprojektf23.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/admin/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authService;
  private final UserService userService;
  private final RoleService roleService;

/*  @PostMapping("/register")
  public ResponseEntity<String> register(
      @RequestBody RegisterRequest request
  ) {
    System.out.println(request);
    return ResponseEntity.ok(authService.register(request));
  }*/

/*  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    System.out.println(request);
    return ResponseEntity.ok(authService.authenticate(request));
  }*/

  @PostMapping("/register")
  public String processRegister(User user) {
    authService.registerDefaultUser(user);

    return "register_success";
  }

  @GetMapping("/users")
  public String listUsers(Model model) {
    List<User> listUsers = userService.getAllUsers();
    model.addAttribute("listUsers", listUsers);

    return "users";
  }

  @GetMapping("/users/edit/{id}")
  public String editUser(@PathVariable("id") Long id, Model model) {
    User user = userService.getUserById(id).get();
    List<Role> listRoles = roleService.getAllRoles();
    model.addAttribute("user", user);
    model.addAttribute("listRoles", listRoles);
    return "user_form";
  }

  @PostMapping("/users/save")
  public String saveUser(User user) {
    userService.save(user);

    return "redirect:/users";
  }

  @GetMapping("/test")
  public ResponseEntity<String> test() {
    return ResponseEntity.ok("Hello from secured endpoint");
  }

}
