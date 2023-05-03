package com.miso.eksamensprojektf23.services;




import com.miso.eksamensprojektf23.models.User;

import java.util.Optional;

public interface UserService {

  Optional<User> getUserByUsername(String username);

}
