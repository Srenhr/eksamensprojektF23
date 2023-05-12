package com.miso.eksamensprojektf23.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "roles")
public class Role {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name = "role_id")
  @JsonIgnore
  private Long roleId;
  @Column(unique = true)
  @NotNull
  private String name;

  @ManyToMany(mappedBy = "roles")
  @ToString.Exclude
  @JsonIgnore
  private Set<User> users;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "roles_privileges",
      joinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "role_id"),
      inverseJoinColumns = @JoinColumn(
          name = "privilege_id", referencedColumnName = "privilege_id"))
  @ToString.Exclude
  @JsonIgnore
  private Set<Privilege> privileges = new HashSet<>();
}