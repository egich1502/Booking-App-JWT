package ru.murashov.serverjwt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

  @GetMapping("")
  public String getMainPage() {
    return "index";
  }

  @GetMapping("tables/{id}/booking")
  public String getSpecificTable() {
    return "specific_table";
  }

  @GetMapping("registration")
  public String registration() {
    return "registration";
  }

  @GetMapping("login")
  public String login() {
    return "login";
  }

//  private final AuthService authService;
//
//  @PreAuthorize("hasAuthority('USER')")
//  @GetMapping("hello/user")
//  public ResponseEntity<String> helloUser() {
//    final JwtAuthentication authInfo = authService.getAuthInfo();
//    return ResponseEntity.ok("Hello user " + authInfo.getPrincipal() + "!");
//  }
//
//  @PreAuthorize("hasAuthority('ADMIN')")
//  @GetMapping("hello/admin")
//  public ResponseEntity<String> helloAdmin() {
//    final JwtAuthentication authInfo = authService.getAuthInfo();
//    return ResponseEntity.ok("Hello admin " + authInfo.getPrincipal() + "!");
//  }
}
