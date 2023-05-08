package com.miso.eksamensprojektf23.repositories;

import com.miso.eksamensprojektf23.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
