package com.miso.eksamensprojektf23.controllers;

import com.miso.eksamensprojektf23.models.Patient;
import com.miso.eksamensprojektf23.models.User;
import com.miso.eksamensprojektf23.services.PatientService;
import com.miso.eksamensprojektf23.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Comparator;
import java.util.List;

@Controller
@CrossOrigin
@RequiredArgsConstructor
public class PatientController {

  private final PatientService patientService;
  private final UserService userService;


  @GetMapping("/patients")
  public String listPatients(Model model) {
    List<Patient> patients = patientService.getAllPatients();
    // Sort the list by the id field
    patients.sort(Comparator.comparingLong(Patient::getPatientId));
    model.addAttribute("patients", patients);
    return "patients";
  }

  @GetMapping("/patient/edit/{id}")
  public String editPatient(@PathVariable("id") Long id, Model model) {
    Patient patient = patientService.getPatientById(id);
    List<User> users = userService.getAllUsers();
    // Sort the list by the id field
    users.sort(Comparator.comparingLong(User::getUserId));
    model.addAttribute("patient", patient);
    model.addAttribute("users", users);
    return "patient_edit_form";
  }

  @PostMapping("/patient/update")
  public String updatePatient(Patient patient) {
    patientService.updatePatient(patient);
    return "redirect:/patients";
  }

  @GetMapping("/patient/register")
  public String registerPatient(Model model) {
    Patient patient = new Patient();
    List<User> users = userService.getAllUsers();
    // Sort the list by the id field
    users.sort(Comparator.comparingLong(User::getUserId));
    model.addAttribute("patient", patient);
    model.addAttribute("users", users);
    return "patient_register_form";
  }

  @PostMapping("/patient/save")
  public String savePatient(Patient patient) {
    patientService.savePatient(patient);
    return "redirect:/patients";
  }

}
