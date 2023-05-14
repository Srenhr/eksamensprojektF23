package com.miso.eksamensprojektf23.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "patients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString /*Remember to add ToString.Exclude to lazy fields, https://www.jpa-buddy.com/blog/lombok-and-jpa-what-may-go-wrong/*/
public class Patient {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "patient_id")
  private Long patientId;
  @NotNull
  private String firstName;
  @NotNull
  private String lastName;
  @Column(unique = true)
  @NotNull
  private String email;
  @Column(unique = true)
  @NotNull
  private String phoneNumber;
  @NotNull
/*
  @DateTimeFormat(pattern = "yyyy-MM-dd")
*/
  private LocalDate birthdate;
  @NotNull
  private String reasonForRefferal;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "employees_patients",
      joinColumns = @JoinColumn(
          name = "patient_id", referencedColumnName = "patient_id"),
      inverseJoinColumns = @JoinColumn(
          name = "user_id", referencedColumnName = "user_id"))
  @ToString.Exclude
  private Set<Employee> employees;

  @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
  @JsonIgnore
  @ToString.Exclude
  private Set<Appointment> appointments;

  @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
  @JsonIgnore
  @ToString.Exclude
  private Set<PatientNote> patientNotes;
}
