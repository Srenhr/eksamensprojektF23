package com.miso.eksamensprojektf23.repositories;

import com.miso.eksamensprojektf23.models.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    // TODO check if this works
    Calendar findCalendarByEmployee_UserId(int employeeId);

    List<Calendar> findCalendarByCalendarId(int calendarId);
}
