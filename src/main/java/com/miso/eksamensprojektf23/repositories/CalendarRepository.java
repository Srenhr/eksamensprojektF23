package com.miso.eksamensprojektf23.repositories;

import com.miso.eksamensprojektf23.models.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    // TODO check if this works
    Calendar findCalendarByEmployee_UserId(int employeeId);
}
