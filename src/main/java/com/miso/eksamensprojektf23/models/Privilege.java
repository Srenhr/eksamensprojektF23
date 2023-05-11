package com.miso.eksamensprojektf23.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "privileges")
public class Privilege {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "privilege_id")
  private Long privilegeId;
  @Column(unique = true)
  @NotNull
  private String name;

  @ManyToMany(mappedBy = "privileges")
  @ToString.Exclude
  @JsonIgnore
  private Set<Role> roles;
}
