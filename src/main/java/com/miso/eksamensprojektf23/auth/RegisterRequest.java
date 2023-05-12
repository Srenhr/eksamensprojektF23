package com.miso.eksamensprojektf23.auth;


import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

  private String username;
  private String password;
  private String firstName;
  private String lastName;
  private String phoneNumber;
  private Set<String> roles;
}
