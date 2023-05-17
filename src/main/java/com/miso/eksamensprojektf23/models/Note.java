package com.miso.eksamensprojektf23.models;

import com.miso.eksamensprojektf23.enums.AppointmentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString /*Remember to add ToString.Exclude to lazy fields, https://www.jpa-buddy.com/blog/lombok-and-jpa-what-may-go-wrong/*/
@Table(name = "notes")
public class Note {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "note_id")
  private Long noteId;

  @Enumerated(EnumType.ORDINAL)
  private AppointmentType appointmentType;

  @NotBlank
  private String textBody;

  // Get the current LocalDateTime
  @Transient
  LocalDateTime currentDateTime = LocalDateTime.now();

  // Create a Timestamp object from the current LocalDateTime
  @NotNull
  Timestamp timestamp = Timestamp.valueOf(currentDateTime);

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "patient_id")
  private Patient patient;
}
