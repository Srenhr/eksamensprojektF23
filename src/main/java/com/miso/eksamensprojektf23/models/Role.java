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
@Table(name = "roles")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "role_id")
  @JsonIgnore
  private Long roleId;
  @Column(unique = true)
  @NotBlank
  private String name;
  @Column(unique = true)
  @NotBlank
  private String tag;

  @ManyToMany(mappedBy = "roles")
  @ToString.Exclude
  @JsonIgnore
  private Set<User> users;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "roles_privileges", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"), inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "privilege_id"))
  @ToString.Exclude
  @JsonIgnore
  private Set<Privilege> privileges;
}