package com.miso.eksamensprojektf23.services.impl;


import com.miso.eksamensprojektf23.models.User;
import com.miso.eksamensprojektf23.repositories.UserRepository;
import com.miso.eksamensprojektf23.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public Optional<User> getUserById(Long id) {
    return userRepository.findById(id);
  }

  @Override
  public Optional<User> getUserByUsername(String username) {
    return userRepository.findUserByUsername(username);
  }

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public void save(User user) {
    userRepository.save(user);
  }
}
