package com.miso.eksamensprojektf23.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "patient_notes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString /*Remember to add ToString.Exclude to lazy fields, https://www.jpa-buddy.com/blog/lombok-and-jpa-what-may-go-wrong/*/
public class PatientNote extends Note {
/*  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "patient_note_id")
  private Long patientNoteId;*/

  @ManyToOne
  @JoinColumn(name = "patient_id")
  private Patient patient;
}
