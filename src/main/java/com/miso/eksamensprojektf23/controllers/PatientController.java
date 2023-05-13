package com.miso.eksamensprojektf23.controllers;

import com.miso.eksamensprojektf23.dtos.PatientDTO;
import com.miso.eksamensprojektf23.models.Employee;
import com.miso.eksamensprojektf23.models.Patient;
import com.miso.eksamensprojektf23.services.EmployeeService;
import com.miso.eksamensprojektf23.services.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/user")
@RequiredArgsConstructor
/*@RestController*/
public class PatientController {


/*    @Autowired
    PatientRepository patientRepository;


    @PostMapping("/createpatient")
    @ResponseStatus(HttpStatus.CREATED)
    public Patient createPatient(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }

    @PostMapping("/createpatient/{EmployeeId}")
    public ResponseEntity<Patient> createPatientFromEmployee(@PathVariable Integer EmployeeId, @RequestBody Patient patient) {
        // TODO

        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }

    @PutMapping("/patient/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        Optional<Patient> optPatient = patientRepository.findById(id);
        if (optPatient.isPresent()) {
            patientRepository.save(patient);
            return new ResponseEntity<>(patient,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

  private final PatientService patientService;
  private final EmployeeService employeeService;


  @GetMapping("/patients")
  public String listPatients(Model model) {
    List<Patient> listPatients = patientService.getAllPatients();
    model.addAttribute("listPatients", listPatients);
    return "patients";
  }

  @GetMapping("/patient/edit/{id}")
  public String editPatient(@PathVariable("id") Long id, Model model) {
    Patient patient = patientService.getPatientById(id);
    List<Employee> listEmployee = employeeService.getAllEmployees();
    model.addAttribute("patient", patient);
    model.addAttribute("listEmployee", listEmployee);
    return "patient_edit_form";
  }

  @PostMapping("/patient/update")
  public String updatePatient( PatientDTO patientDTO) {
    patientService.updatePatient(patientDTO);
    return "redirect:/user/patients";
  }

  @GetMapping("/patient/register")
  public String editPatient(Model model) {
    PatientDTO patientDTO = new PatientDTO();
    List<Employee> listEmployee = employeeService.getAllEmployees();
    model.addAttribute("patientDTO", patientDTO);
    model.addAttribute("listEmployee", listEmployee);
    return "patient_register_form";
  }

  @PostMapping("/patient/save")
  public String savePatient( PatientDTO patientDTO) {
    System.out.println(patientDTO);
    patientService.savePatient(patientDTO);
    return "redirect:/user/patients";
  }

}
