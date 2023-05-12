package com.miso.eksamensprojektf23.controllers;


import com.miso.eksamensprojektf23.models.Calendar;
import com.miso.eksamensprojektf23.repositories.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CalendarController {
    
    @Autowired
    CalendarRepository calendarRepository;
    
    
    
    @GetMapping("/calendar/{employeeId}")
    public Calendar getCalendarByEmployeeId(@PathVariable int employeeId) {
        // TODO add optional to
        return calendarRepository.findCalendarByEmployee_UserId(employeeId);
        /*
        Optional<Calendar> optKommune = calendarRepository.findById(employeeId);
        if (optKommune.isPresent()) {
            calendarRepository.save();
            return new ResponseEntity<>(, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

         */
    }

}
