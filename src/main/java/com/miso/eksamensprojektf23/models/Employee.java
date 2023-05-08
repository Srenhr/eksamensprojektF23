package com.miso.eksamensprojektf23.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "employees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString /*Remember to add ToString.Exclude to lazy fields, https://www.jpa-buddy.com/blog/lombok-and-jpa-what-may-go-wrong/*/
public class Employee extends User {
  @NotNull
  private String firstName;
  @NotNull
  private String lastName;
  @Column(unique = true)
  @NotNull
  private String phoneNumber;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "employees_patients",
      joinColumns = @JoinColumn(
          name = "user_id", referencedColumnName = "user_id"),
      inverseJoinColumns = @JoinColumn(
          name = "patient_id", referencedColumnName = "patient_id"))
  @ToString.Exclude
  private Set<Patient> patients;

  @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
  @JsonIgnore
  @ToString.Exclude
  private Calendar calendar;

  @Builder(builderMethodName = "employeeBuilder")
  public Employee(Long userId, @NotNull String username, @NotNull String password, Set<Role> roles, String firstName, String lastName, String phoneNumber) {
    super(userId, username, password, roles);
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
  }
}
