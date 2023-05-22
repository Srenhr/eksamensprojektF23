package com.miso.eksamensprojektf23.init;


import com.miso.eksamensprojektf23.enums.AppointmentType;
import com.miso.eksamensprojektf23.models.*;
import com.miso.eksamensprojektf23.repositories.*;
import com.miso.eksamensprojektf23.services.PatientService;
import com.miso.eksamensprojektf23.services.UserService;
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
  private final UserService userService;
  private final RoleRepository roleRepository;
  private final PrivilegeRepository privilegeRepository;
  private final PatientRepository patientRepository;
  private final PatientService patientService;
  private final NoteRepository noteRepository;
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
    Role roleAdmin = createRoleIfNotFound("ROLE_ADMIN", "Admin", adminPrivileges);
    Role roleUser = createRoleIfNotFound("ROLE_USER", "Bruger", userPrivileges);

    User user = new User();
    user.setFirstName("admin");
    user.setLastName("adminsen");
    user.setPassword(passwordEncoder.encode("test1234"));
    user.setUsername("admin@test.com");
    user.setPhoneNumber("+4512345678");
    user.setEnabled(true);
    user.setRoles(new HashSet<>(Arrays.asList(roleAdmin, roleUser)));
    userRepository.save(user);

    user = new User();
    user.setFirstName("user1");
    user.setLastName("usersen");
    user.setPassword(passwordEncoder.encode("test1234"));
    user.setUsername("user@test.com");
    user.setPhoneNumber("+4587654321");
    user.setEnabled(true);
    user.setRoles(new HashSet<>(Collections.singletonList(roleUser)));
    userRepository.save(user);

    user = new User();
    user.setFirstName("user2");
    user.setLastName("usersen");
    user.setPassword(passwordEncoder.encode("test1234"));
    user.setUsername("user2@test.com");
    user.setPhoneNumber("+4574938754");
    user.setEnabled(false);
    user.setRoles(new HashSet<>(Collections.singletonList(roleUser)));
    userRepository.save(user);

    Patient patient = new Patient();
    patient.setFirstName("patient1");
    patient.setLastName("patientsen");
    patient.setEmail("patient1@test.com");
    patient.setPhoneNumber("+4512546576");
    patient.setSSNumber("1234-210292");
    patient.setBirthdate(LocalDate.parse("1992-02-21"));
    patient.setReasonForRefferal("blind");
    patient.setUsers(new HashSet<>(Collections.singletonList(userService.getUserById(1L))));
    patientRepository.save(patient);

    patient = new Patient();
    patient.setFirstName("patient2");
    patient.setLastName("patientsen");
    patient.setEmail("patient2@test.com");
    patient.setPhoneNumber("+4523456789");
    patient.setSSNumber("4321-210491");
    patient.setBirthdate(LocalDate.parse("1991-04-21"));
    patient.setReasonForRefferal("blind");
    patient.setUsers(new HashSet<>(Collections.singletonList(userService.getUserById(2L))));
    patientRepository.save(patient);

    patient = new Patient();
    patient.setFirstName("patient3");
    patient.setLastName("patientsen");
    patient.setEmail("patient3@test.com");
    patient.setPhoneNumber("+4586313456");
    patient.setSSNumber("5678-130722");
    patient.setBirthdate(LocalDate.parse("2022-07-13"));
    patient.setReasonForRefferal("blind");
    patient.setUsers(new HashSet<>(userRepository.findAll()));
    patientRepository.save(patient);

    Note note = new Note();
    note.setAppointmentType(AppointmentType.UNDERVISNING);
    note.setPatient(patientService.getPatientById(1L));
    note.setUser(userService.getUserById(1L));
    note.setTextBody("HELLO WORLD!");
    noteRepository.save(note);

    alreadySetup = true;
  }

  @Transactional
  public Privilege createPrivilegeIfNotFound(String name) {

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
  public Role createRoleIfNotFound(
      String name, String tag, Set<Privilege> privileges) {

    Optional<Role> optRole = roleRepository.findRoleByName(name);
    Role role = null;
    if (optRole.isEmpty()) {
      role = new Role();
      role.setName(name);
      role.setTag(tag);
      role.setPrivileges(privileges);
      roleRepository.save(role);
    }
    return role;
  }
}
