package com.miso.eksamensprojektf23.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin
public class DefaultController {

  @GetMapping("/")
  public String home1() {
    return "index";
  }

  @GetMapping("/home")
  public String home() {
    return "index";
  }

  @GetMapping("/admin")
  public String admin() {
    return "admin";
  }

  @GetMapping("/user")
  public String user() {
    return "user";
  }

  @GetMapping("/about")
  public String about() {
    return "about";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/failure")
  public String error401() {
    return "error/401";
  }

  @GetMapping("/403")
  public String error403() {
    return "error/403";
  }

  @GetMapping("/test")
  public String test() {
    return "tempEmployeeFormTest";
  }
}
