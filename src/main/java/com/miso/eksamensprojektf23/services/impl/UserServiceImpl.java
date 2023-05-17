package com.miso.eksamensprojektf23.services.impl;


import com.miso.eksamensprojektf23.models.Patient;
import com.miso.eksamensprojektf23.models.Role;
import com.miso.eksamensprojektf23.models.User;
import com.miso.eksamensprojektf23.repositories.RoleRepository;
import com.miso.eksamensprojektf23.repositories.UserRepository;
import com.miso.eksamensprojektf23.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public User getUserById(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
  }

  @Override
  public User getUserByUsername(String username) {
    return userRepository.findUserByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
  }

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public void updateUser(User request) {
    User user = userRepository.findById(request.getUserId())
        .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + request.getUserId()));
    ;
    user.setUsername(request.getUsername());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setPhoneNumber(request.getPhoneNumber());
    user.setEnabled(request.isEnabled());
    user.setRoles(request.getRoles());
    Role roleUser = roleRepository.findRoleByName("ROLE_USER")
        .orElseThrow(() -> new EntityNotFoundException("Role not found with name: ROLE_USER"));
    user.addRole(roleUser);
    userRepository.save(user);
  }

  public void saveDefaultUser(User user) {
    Role roleUser = roleRepository.findRoleByName("ROLE_USER")
        .orElseThrow(() -> new EntityNotFoundException("Role not found with name: ROLE_USER"));
    user.addRole(roleUser);
    user.setEnabled(true);
    userRepository.save(user);
  }

  public List<Patient> getPatientsByUsername(String username) {
    User user = userRepository.findUserByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + username));
    if (user != null) {
      Set<Patient> patients = user.getPatients();
      // Convert Set to List
      return new ArrayList<>(patients);
    } else {
      return new ArrayList<>();
    }
  }

}
