package com.miso.eksamensprojektf23.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.text.SimpleDateFormat;
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

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "patient_id")
  private Patient patient;

  @NotNull
  private String title;

  @NotNull(message = "Appointment start time is required")
  @Temporal(TemporalType.TIMESTAMP)
  private Calendar startTime;

  @NotNull(message = "Appointment end time is required")
  @Temporal(TemporalType.TIMESTAMP)
  private Calendar endTime;

  // Other attributes, constructors, getters, and setters

  // Helper method to get appointment duration in minutes
  public int getDurationInMinutes() {
    long durationInMillis = endTime.getTimeInMillis() - startTime.getTimeInMillis();
    return (int) (durationInMillis / (1000 * 60));
  }

  // Helper method to get the appointment start time as an ISO 8601 string
  public String getStartTimeIso8601() {
    SimpleDateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    return iso8601Format.format(startTime.getTime());
  }

  // Helper method to get the appointment end time as an ISO 8601 string
  public String getEndTimeIso8601() {
    SimpleDateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    return iso8601Format.format(endTime.getTime());
  }

  // Other methods or business logic related to the appointment

}
