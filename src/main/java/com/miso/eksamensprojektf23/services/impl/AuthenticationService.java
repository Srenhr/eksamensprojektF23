package com.miso.eksamensprojektf23.services.impl;


import com.miso.eksamensprojektf23.repositories.RoleRepository;
import com.miso.eksamensprojektf23.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

/*  public String register(UserDTO userDTO) {
    var user = Employee.employeeBuilder()
        .username(userDTO.getUsername())
        .password(passwordEncoder.encode(userDTO.getPassword()))
        .firstName(userDTO.getFirstName())
        .lastName(userDTO.getLastName())
        .phoneNumber(userDTO.getPhoneNumber())
*//*
        .roles(request.getRoles())
*//*
        .build();
    System.out.println(userDTO.getRoles());
    try {
      userRepository.save(user);
      return "User successfully saved in database";
    } catch (DataAccessException e) {
      return "User with username: " + user.getUsername() + " already exists in database";
    }
  }*/



}
