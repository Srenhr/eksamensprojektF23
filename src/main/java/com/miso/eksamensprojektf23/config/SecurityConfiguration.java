package com.miso.eksamensprojektf23.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final AuthenticationProvider authenticationProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authenticationProvider(authenticationProvider)
        .cors()
        .and()
        .csrf()
        .disable()
        .authorizeHttpRequests((authz) -> authz
            .requestMatchers("/resources/**", "/static/**", "/", "/home", "/about", "/error/**", "/**","/*", "/public/**")
            .permitAll()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/user/**").hasRole("USER")
            .anyRequest()
            .authenticated())
        .formLogin()
        .loginPage("/login")
        .failureUrl("/failure")
        .permitAll()
        .and()
        .logout()
        .logoutSuccessUrl("/home")
        .invalidateHttpSession(true)
        .and()
        .exceptionHandling();
/*
        .accessDeniedHandler(accessDeniedHandler());
*/

    return http.build();
  }
}
