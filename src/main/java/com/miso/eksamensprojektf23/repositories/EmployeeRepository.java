package com.miso.eksamensprojektf23.repositories;

import com.miso.eksamensprojektf23.models.Employee;
import com.miso.eksamensprojektf23.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  Optional<Employee> findEmployeeByUsername(String username);

}
