package com.miso.eksamensprojektf23.auth;


import com.miso.eksamensprojektf23.models.Employee;
import com.miso.eksamensprojektf23.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public String register(RegisterRequest request) {
    var user = Employee.employeeBuilder()
        .username(request.getUsername())
        .password(passwordEncoder.encode(request.getPassword()))
        .firstName(request.getFirstName())
        .lastName(request.getLastName())
        .phoneNumber(request.getPhoneNumber())
        .roles(request.getRoles())
        .build();
    try {
      userRepository.save(user);
      return "User successfully saved in database";
    } catch (DataAccessException e) {
      return "User with username: " + user.getUsername() + " already exists in database";
    }
  }

}
