package com.miso.eksamensprojektf23.services.impl;


import com.miso.eksamensprojektf23.models.Employee;
import com.miso.eksamensprojektf23.repositories.EmployeeRepository;
import com.miso.eksamensprojektf23.services.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;

  @Override
  public List<Employee> getAllEmployees() {
    return employeeRepository.findAll();
  }

  @Override
  public Employee getEmployeeById(Long id) {
    return employeeRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));
  }
}
