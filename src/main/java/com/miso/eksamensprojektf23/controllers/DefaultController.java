package com.miso.eksamensprojektf23.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin
@RequiredArgsConstructor
public class DefaultController {

  @GetMapping("/")
  public String home1() {
    return "index";
  }

  @GetMapping("/home")
  public String home() {
    return "index";
  }

  @GetMapping("/dashboard")
  public String dashboard() {
    return "dashboard";
  }

  @GetMapping("/calendar")
  public String calendar() {
    return "calendar";
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
    return "errors/401";
  }

  @GetMapping("/403")
  public String error403() {
    return "errors/403";
  }

  @GetMapping("/test")
  public String test() {
    return "calendarImplementationTest";
  }
}
