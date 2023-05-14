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
  @Transient
  private Long userId; /*This attributes only purpose is to make sure thymeleaf works properly, when returning objects from view.*/

  @Column(unique = true)
  @NotNull
  private String department; /*Not used for anything - just for show*/

  @ManyToMany(mappedBy = "employees")
  @ToString.Exclude
  private Set<Patient> patients;

  @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
  @JsonIgnore
  @ToString.Exclude
  private Calendar calendar;

  @Builder(builderMethodName = "employeeBuilder")
  public Employee(Long userId, @NotNull String username, @NotNull String password, @NotNull String firstName, @NotNull String lastName, @NotNull String phoneNumber, Set<Role> roles, Long userId1, String department) {
    super(userId, username, password, firstName, lastName, phoneNumber, roles);
    this.userId = userId1;
    this.department = department;
  }
}
