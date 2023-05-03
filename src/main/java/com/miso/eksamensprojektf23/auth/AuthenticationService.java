package com.miso.eksamensprojektf23.auth;


import com.miso.eksamensprojektf23.models.User;
import com.miso.eksamensprojektf23.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    var user = User.builder()
        .username(request.getUsername())
        .password(passwordEncoder.encode(request.getPassword()))
/*        .firstName(request.getFirstName())
        .lastName(request.getLastName())*/
        .roles(request.getRoles())
        .build();
    userRepository.save(user);
    return AuthenticationResponse.builder()
        .token("jwtToken")
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()
        )
    );
    var user = userRepository.findUserByUsername(request.getUsername())
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    return AuthenticationResponse.builder()
        .token("jwtToken")
        .build();
  }

}
