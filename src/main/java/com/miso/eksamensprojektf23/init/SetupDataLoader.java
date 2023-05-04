package com.miso.eksamensprojektf23.init;


import com.miso.eksamensprojektf23.models.Employee;
import com.miso.eksamensprojektf23.models.Privilege;
import com.miso.eksamensprojektf23.models.Role;
import com.miso.eksamensprojektf23.repositories.PrivilegeRepository;
import com.miso.eksamensprojektf23.repositories.RoleRepository;
import com.miso.eksamensprojektf23.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements
    ApplicationListener<ContextRefreshedEvent> {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PrivilegeRepository privilegeRepository;
  private final PasswordEncoder passwordEncoder;
  private boolean alreadySetup = false;

  @Override
  @Transactional
  public void onApplicationEvent(ContextRefreshedEvent event) {
    if (alreadySetup)
      return;
    Privilege readPrivilege
        = createPrivilegeIfNotFound("READ_PRIVILEGE");
    Privilege writePrivilege
        = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

    Set<Privilege> adminPrivileges = new HashSet<>(Arrays.asList(
        readPrivilege, writePrivilege));
    Role roleAdmin = createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
    Role roleUser = createRoleIfNotFound("ROLE_USER", new HashSet<>(Collections.singletonList(readPrivilege)));

    Employee user = new Employee();
    user.setFirstName("test");
    user.setLastName("test");
    user.setPassword(passwordEncoder.encode("test"));
    user.setUsername("admin@test.com");
    user.setPhoneNumber("+45123456789");
    user.setRoles(new HashSet<>(Collections.singletonList(roleAdmin)));
    userRepository.save(user);

    user = new Employee();
    user.setFirstName("test");
    user.setLastName("test");
    user.setPassword(passwordEncoder.encode("test"));
    user.setUsername("employee@test.com");
    user.setPhoneNumber("+45987654321");
    user.setRoles(new HashSet<>(Collections.singletonList(roleUser)));
    userRepository.save(user);

    alreadySetup = true;
  }

  @Transactional
  Privilege createPrivilegeIfNotFound(String name) {

    Optional<Privilege> optPrivilege = privilegeRepository.findPrivilegeByName(name);
    Privilege privilege = null;
    if (optPrivilege.isEmpty()) {
      privilege = new Privilege();
      privilege.setName(name);
      privilegeRepository.save(privilege);
    }
    return privilege;
  }

  @Transactional
  Role createRoleIfNotFound(
      String name, Set<Privilege> privileges) {

    Optional<Role> optRole = roleRepository.findRoleByName(name);
    Role role = null;
    if (optRole.isEmpty()) {
      role = new Role();
      role.setName(name);
      role.setPrivileges(privileges);
      roleRepository.save(role);
    }
    return role;
  }
}
