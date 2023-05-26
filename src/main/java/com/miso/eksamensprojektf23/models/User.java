package com.miso.eksamensprojektf23.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString /*Remember to add ToString.Exclude to lazy fields, https://www.jpa-buddy.com/blog/lombok-and-jpa-what-may-go-wrong/*/
@Table(name = "users")
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long userId;
  @Column(unique = true)
  @NotBlank
  private String username; /*email*/
  @NotBlank
  @JsonIgnore
  private String password;
  @NotBlank
  @JsonIgnore
  private String firstName;
  @NotBlank
  @JsonIgnore
  private String lastName;
  @Transient
  private String name;
  @Column(unique = true)
  @NotBlank
  @JsonIgnore
  private String phoneNumber;
  @NotNull
  @JsonIgnore
  private boolean enabled;
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "users_roles",
      joinColumns = @JoinColumn(
          name = "user_id", referencedColumnName = "user_id"),
      inverseJoinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "role_id"))
  @ToString.Exclude
  @JsonIgnore
  private Set<Role> roles;

  @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
  @ToString.Exclude
  @JsonIgnore
  private Set<Patient> patients;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  @ToString.Exclude
  @JsonIgnore
  private Set<Appointment> appointments;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  @ToString.Exclude
  @JsonIgnore
  private Set<Note> notes;


  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    {
      List<GrantedAuthority> authorities
          = new ArrayList<>();
      for (Role role : roles) {
        authorities.add(new SimpleGrantedAuthority(role.getName()));
        role.getPrivileges().stream()
            .map(p -> new SimpleGrantedAuthority(p.getName()))
            .forEach(authorities::add);
      }
      return authorities;
    }
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  public String getName() {
    return this.firstName + " " + this.lastName;
  }

  public void addRole(Role role) {
    roles.add(role);
  }
}
