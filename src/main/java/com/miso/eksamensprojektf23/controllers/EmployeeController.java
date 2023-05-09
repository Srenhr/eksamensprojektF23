package com.miso.eksamensprojektf23.controllers;

import com.miso.eksamensprojektf23.models.Employee;
import com.miso.eksamensprojektf23.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;


    @PostMapping("/createEmployee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        // Maybe change this for optional somehow to check for .IsEmpty or .IsPresent and then .orElse??
        employeeRepository.save(employee);

        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }


}
