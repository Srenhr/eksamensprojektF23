package com.miso.eksamensprojektf23.repositories;

import com.miso.eksamensprojektf23.models.Date;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DateRepository extends JpaRepository<Date, Long> {

    List<Date> findDatesByCalendar_CalendarId(long calendarId);

}
