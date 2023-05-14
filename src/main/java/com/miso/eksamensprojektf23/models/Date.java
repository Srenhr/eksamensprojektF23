package com.miso.eksamensprojektf23.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dates")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString /*Remember to add ToString.Exclude to lazy fields, https://www.jpa-buddy.com/blog/lombok-and-jpa-what-may-go-wrong/*/
public class Date {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "date_id")
  private Long dateId;

  @NotNull
  private LocalDate date;

  @ManyToOne
  @JoinColumn(name = "calendar_id")
  private Calendar calendar;

  @OneToMany(mappedBy = "date", cascade = CascadeType.ALL)
  @JsonIgnore
  @ToString.Exclude
  private Set<Appointment> appointments = new HashSet<>();
}
