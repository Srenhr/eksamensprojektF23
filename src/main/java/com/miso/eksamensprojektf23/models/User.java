package com.miso.eksamensprojektf23.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString /*Remember to add ToString.Exclude to lazy fields, https://www.jpa-buddy.com/blog/lombok-and-jpa-what-may-go-wrong/*/
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long userId;
  @Column(unique = true)
  @NotNull
  private String username; /*email*/
  @NotNull
  private String password;
  @NotNull
  private String firstName;
  @NotNull
  private String lastName;
  @Column(unique = true)
  @NotNull
  private String phoneNumber;
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

  public void addRole(Role role) {
    this.roles.add(role);
  }

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
    return true;
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
}
