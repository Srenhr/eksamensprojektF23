package com.miso.eksamensprojektf23.services;


import com.miso.eksamensprojektf23.models.User;

import java.util.List;

public interface UserService {

  User getUserById(Long id);

  List<User> getAllUsers();

  void updateUser(User user);

  void saveDefaultUser(User user);

}
