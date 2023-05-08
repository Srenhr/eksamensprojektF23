package com.miso.eksamensprojektf23.controllers;

import com.miso.eksamensprojektf23.models.Patient;
import com.miso.eksamensprojektf23.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PatientController {


    @Autowired
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
    public ResponseEntity<Patient> updatePatient(@PathVariable int id, @RequestBody Patient patient) {
        Optional<Patient> optPatient = patientRepository.findById(id);
        if (optPatient.isPresent()) {
            patientRepository.save(patient);
            return new ResponseEntity<>(patient,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
