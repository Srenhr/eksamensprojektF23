package com.miso.eksamensprojektf23.controllers;

import com.miso.eksamensprojektf23.models.Role;
import com.miso.eksamensprojektf23.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@RestController
public class RolesController {

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/getRoles")
    public Collection<Role> getRoles() {
        return roleRepository.findAll();
    }

}
