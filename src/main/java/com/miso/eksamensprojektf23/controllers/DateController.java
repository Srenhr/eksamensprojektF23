package com.miso.eksamensprojektf23.controllers;


import com.miso.eksamensprojektf23.models.Date;
import com.miso.eksamensprojektf23.repositories.CalendarRepository;
import com.miso.eksamensprojektf23.repositories.DateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;

@RestController
@CrossOrigin
public class DateController {

    @Autowired
    DateRepository dateRepository;

    @Autowired
    CalendarRepository calendarRepository;

    @GetMapping("/date/{calendarId}")
    public List<Date> getDatesByCalendarId(@PathVariable long calendarId) {
        List<Date> dates = dateRepository.findDatesByCalendar_CalendarId(calendarId);
        return dates;
    }


}
