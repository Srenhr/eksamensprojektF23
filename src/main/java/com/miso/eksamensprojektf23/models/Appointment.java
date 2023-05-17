package com.miso.eksamensprojektf23.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Calendar;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString /*Remember to add ToString.Exclude to lazy fields, https://www.jpa-buddy.com/blog/lombok-and-jpa-what-may-go-wrong/*/
@Table(name = "appointments")
public class Appointment {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name = "appointment_id")
  private Long appointmentId;

  @Temporal(TemporalType.TIMESTAMP)
  private Calendar appointmentDate;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "patient_id")
  private Patient patient;
}
