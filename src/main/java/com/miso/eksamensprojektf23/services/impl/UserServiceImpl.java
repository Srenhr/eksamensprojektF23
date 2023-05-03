package com.miso.eksamensprojektf23.services.impl;



import com.miso.eksamensprojektf23.models.User;
import com.miso.eksamensprojektf23.repositories.UserRepository;
import com.miso.eksamensprojektf23.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public Optional<User> getUserByUsername(String username) {
    return userRepository.findUserByUsername(username);

  }


}
