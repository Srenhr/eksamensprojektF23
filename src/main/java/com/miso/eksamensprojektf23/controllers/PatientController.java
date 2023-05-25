package com.miso.eksamensprojektf23.controllers;

import com.miso.eksamensprojektf23.models.Patient;
import com.miso.eksamensprojektf23.models.User;
import com.miso.eksamensprojektf23.services.PatientService;
import com.miso.eksamensprojektf23.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
  public String updatePatient(Patient patient, RedirectAttributes redirectAttributes) {
    patientService.updatePatient(patient);
    redirectAttributes.addFlashAttribute("message", "Borgeren er blevet opdateret i databasen.");
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
  public String savePatient(Patient patient, RedirectAttributes redirectAttributes) {
    try {
      patientService.savePatient(patient);
      redirectAttributes.addFlashAttribute("message", "Borgeren er blevet gemt i databasen.");
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("message", "En borger med samme CPR-nummer eller Telefonnummer findes allerede i databasen.");
      return "redirect:/patient/register";
    }
    return "redirect:/patients";
  }

}
