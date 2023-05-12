package com.miso.eksamensprojektf23.services.impl;

import com.miso.eksamensprojektf23.models.Role;
import com.miso.eksamensprojektf23.repositories.RoleRepository;
import com.miso.eksamensprojektf23.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;

  @Override
  public List<Role> getAllRoles() {
    return roleRepository.findAll();
  }
}
