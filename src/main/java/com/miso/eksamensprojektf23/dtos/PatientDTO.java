package com.miso.eksamensprojektf23.dtos;

import com.miso.eksamensprojektf23.models.Employee;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PatientDTO {

  private Long patientId;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;

  /*
    @DateTimeFormat(pattern = "yyyy-MM-dd")
  */
  private LocalDate birthdate;
  private String reasonForRefferal;
  private Set<Long> userIds;
}
