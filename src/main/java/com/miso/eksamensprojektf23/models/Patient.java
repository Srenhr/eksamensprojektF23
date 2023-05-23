package com.miso.eksamensprojektf23.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString /*Remember to add ToString.Exclude to lazy fields, https://www.jpa-buddy.com/blog/lombok-and-jpa-what-may-go-wrong/*/
@Table(name = "patients")
public class Patient {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "patient_id")
  private Long patientId;
  @NotBlank
  private String firstName;
  @NotBlank
  private String lastName;
  @Transient
  private String name;
  @Column(unique = true)
  @NotBlank
  private String email;
  @Column(unique = true)
  @NotBlank
  private String phoneNumber;
  @Column(unique = true)
  @NotBlank
  @JsonIgnore
  private String sSNumber;
  @NotNull
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthdate;
  @NotBlank
  private String reasonForRefferal;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "users_patients",
      joinColumns = @JoinColumn(
          name = "patient_id", referencedColumnName = "patient_id"),
      inverseJoinColumns = @JoinColumn(
          name = "user_id", referencedColumnName = "user_id"))
  @ToString.Exclude
  private Set<User> users;

  @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
  @JsonIgnore
  @ToString.Exclude
  private Set<Appointment> appointments;

  @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
  @JsonIgnore
  @ToString.Exclude
  private Set<Note> notes;

  public String getName() {
    return this.firstName + " " + this.lastName;
  }
}
