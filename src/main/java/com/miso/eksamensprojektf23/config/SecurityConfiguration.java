package com.miso.eksamensprojektf23.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
            .requestMatchers("/resources/**", "/static/**","/", "/home", "/about", "/error/**")
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

  // create two users, admin and user, both with the password: 123
/*  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    System.out.println("Users er oprettet.");

    auth.inMemoryAuthentication()
        .withUser("USER")
        .password("$2a$12$Imos8GG8/L4CoYa/YsvgaOisFFnSh7OsnP0LyCb.AgOCjJGEEtU3S")
        .roles("USER")
        .and()
        .withUser("ADMIN")
        .password("$2a$12$Imos8GG8/L4CoYa/YsvgaOisFFnSh7OsnP0LyCb.AgOCjJGEEtU3S")
        .roles("ADMIN", "USER");
  }*/

/*  @Bean
  public AuthenticationFailureHandler authenticationFailureHandler() {
    return new CustomAuthenticationFailureHandler();
  }*/

/*  @Bean
  public AuthenticationSuccessHandler authenticationSuccessHandler() {
    return new CustomAuthenticationSuccessHandler();
  }*/

/*  @Bean
  public AccessDeniedHandler accessDeniedHandler() {
    return new CustomAccessDeniedHandler();
  }*/

}
