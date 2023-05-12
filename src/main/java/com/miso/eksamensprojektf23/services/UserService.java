package com.miso.eksamensprojektf23.services;




import com.miso.eksamensprojektf23.models.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserService {

  Optional<User> getUserById(Long id);

  Optional<User> getUserByUsername(String username);

  List<User> getAllUsers();

  void save(User user);

}
