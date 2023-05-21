package com.miso.eksamensprojektf23.services;


import com.miso.eksamensprojektf23.models.Patient;
import com.miso.eksamensprojektf23.models.User;

import java.util.List;
import java.util.Set;

public interface UserService {

  User getUserById(Long id);

  User getUserByUsername(String username);

  List<User> getAllUsers();

  void updateUser(User user);

  void saveDefaultUser(User user);

  List<Patient> getPatientsByUsername(String username);

}
