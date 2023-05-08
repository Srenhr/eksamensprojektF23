package com.miso.eksamensprojektf23.repositories;

import com.miso.eksamensprojektf23.models.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
}
