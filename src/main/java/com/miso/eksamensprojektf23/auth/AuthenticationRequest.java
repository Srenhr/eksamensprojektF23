package com.miso.eksamensprojektf23.auth;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AuthenticationRequest {

  private String username;

  private String password;
}
