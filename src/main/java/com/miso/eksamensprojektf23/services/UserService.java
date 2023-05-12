package com.miso.eksamensprojektf23.services;




import com.miso.eksamensprojektf23.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

  User getUserById(Long id);

  List<User> getAllUsers();

  void updateUser(User user);

  void registerDefaultUser(User user);

}
