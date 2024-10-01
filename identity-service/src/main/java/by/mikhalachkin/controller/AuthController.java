package by.mikhalachkin.controller;

import by.mikhalachkin.dto.AuthRequest;
import by.mikhalachkin.entity.UserCredential;
import by.mikhalachkin.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;


@RestController
@RequestMapping
public class AuthController {
  @Autowired
  private AuthService service;

  @Autowired
  private AuthenticationManager authenticationManager;

  @PostMapping("/register")
  public String addNewUser(@RequestBody UserCredential user) {
    return service.saveUser(user);
  }

  @PostMapping("/token")
  public String getToken(@RequestBody AuthRequest authRequest) {
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
    );
    if (authentication.isAuthenticated()) {
      return service.generateToken(authRequest.getUsername());
    } else {
      throw new RuntimeException("Invalid access.");
    }
  }

  @GetMapping("/validate")
  public String validateToken(@RequestParam("token") String token) {
    service.validateToken(token);
    return "Token is valid";
  }
}
