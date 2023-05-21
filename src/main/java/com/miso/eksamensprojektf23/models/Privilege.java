package com.miso.eksamensprojektf23.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString /*Remember to add ToString.Exclude to lazy fields, https://www.jpa-buddy.com/blog/lombok-and-jpa-what-may-go-wrong/*/
@Table(name = "privileges")
public class Privilege {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "privilege_id")
  @JsonIgnore
  private Long privilegeId;
  @Column(unique = true)
  @NotBlank
  private String name;

  @ManyToMany(mappedBy = "privileges")
  @ToString.Exclude
  @JsonIgnore
  private Set<Role> roles;
}
