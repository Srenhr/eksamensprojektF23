package com.miso.eksamensprojektf23.init;


import com.miso.eksamensprojektf23.models.Employee;
import com.miso.eksamensprojektf23.models.Patient;
import com.miso.eksamensprojektf23.models.Privilege;
import com.miso.eksamensprojektf23.models.Role;
import com.miso.eksamensprojektf23.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements
    ApplicationListener<ContextRefreshedEvent> {

  private final UserRepository userRepository;

  private final EmployeeRepository employeeRepository;
  private final RoleRepository roleRepository;
  private final PrivilegeRepository privilegeRepository;
  private final PatientRepository patientRepository;
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
    Privilege updatePrivilege
        = createPrivilegeIfNotFound("UPDATE_PRIVILEGE");
    Privilege deletePrivilege
        = createPrivilegeIfNotFound("DELETE_PRIVILEGE");

    Set<Privilege> adminPrivileges = new HashSet<>(Arrays.asList(
        readPrivilege, writePrivilege, updatePrivilege, deletePrivilege));
    Set<Privilege> userPrivileges = new HashSet<>(Arrays.asList(readPrivilege, writePrivilege, updatePrivilege));
    Role roleAdmin = createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
    Role roleUser = createRoleIfNotFound("ROLE_USER", userPrivileges);

    Employee employee = new Employee();
    employee.setFirstName("test");
    employee.setLastName("test");
    employee.setPassword(passwordEncoder.encode("test"));
    employee.setUsername("admin@test.com");
    employee.setPhoneNumber("+4512345678");
    employee.setRoles(new HashSet<>(Collections.singletonList(roleAdmin)));
    employee.setDepartment("Development");
    employeeRepository.save(employee);

    employee = new Employee();
    employee.setFirstName("test");
    employee.setLastName("test");
    employee.setPassword(passwordEncoder.encode("test"));
    employee.setUsername("employee@test.com");
    employee.setPhoneNumber("+4587654321");
    employee.setRoles(new HashSet<>(Collections.singletonList(roleUser)));
    employee.setDepartment("Operations");
    employeeRepository.save(employee);

    Patient patient = new Patient();
    patient.setFirstName("test");
    patient.setLastName("test");
    patient.setEmail("patient@test.com");
    patient.setPhoneNumber("+4523456789");
    patient.setBirthdate(LocalDate.parse("1991-04-21"));
    patient.setReasonForRefferal("test");
    patient.setEmployees(new HashSet<>(employeeRepository.findAll()));
    patientRepository.save(patient);

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
