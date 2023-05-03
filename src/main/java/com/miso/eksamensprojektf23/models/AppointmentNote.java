package com.miso.eksamensprojektf23.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
/*
@Table(name = "appointment_notes")
*/
@DiscriminatorValue("2")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString /*Remember to add ToString.Exclude to lazy fields, https://www.jpa-buddy.com/blog/lombok-and-jpa-what-may-go-wrong/*/
public class AppointmentNote extends Note {
/*  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "appointment_note_id")
  private Long appointmentNoteId;*/

  private String patientextra2;

  @OneToOne
  @JoinColumn(name = "appointment_id")
  private Appointment appointment;
}
