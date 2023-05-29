package com.miso.eksamensprojektf23.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "appointment_id")
  private Long appointmentId;

  @ManyToOne
  @JoinColumn(name = "user_id")
  @JsonIgnore
  private User user;

  @NotBlank
  private String title;

  @Lob
  @Column(length = 65535) /*Makes sure String is persisted as TEXT in database instead of varchar(255)*/
  private String description;

  @NotNull(message = "Appointment start time is required")
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") /*ISO8601*/
  private LocalDateTime startTime;

  @NotNull(message = "Appointment end time is required")
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") /*ISO8601*/
  private LocalDateTime endTime;

  // Other attributes, constructors, getters, and setters

  // Helper method to get appointment duration in minutes
  public int getDurationInMinutes() {
    return endTime.getMinute() - startTime.getMinute();
  }

  // Other methods or business logic related to the appointment

}
