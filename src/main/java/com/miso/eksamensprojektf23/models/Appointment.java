package com.miso.eksamensprojektf23.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString /*Remember to add ToString.Exclude to lazy fields, https://www.jpa-buddy.com/blog/lombok-and-jpa-what-may-go-wrong/*/
public class Appointment {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "appointment_id")
  private Long appointmentId;

  @ManyToOne
  @JoinColumn(name = "date_id")
  private Date date;

  @ManyToOne
  @JoinColumn(name = "patient_id")
  private Patient patient;

  @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
  @JsonIgnore
  @ToString.Exclude
  private AppointmentNote appointmentNote;
}
