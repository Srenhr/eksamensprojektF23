package com.miso.eksamensprojektf23.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "calendars")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString /*Remember to add ToString.Exclude to lazy fields, https://www.jpa-buddy.com/blog/lombok-and-jpa-what-may-go-wrong/*/
public class Calendar {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name = "calendar_id")
  private Long calendarId;

  @OneToOne
  @JoinColumn(name = "employee_id")
  private Employee employee;

  @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL)
  @JsonIgnore
  @ToString.Exclude
  private Set<Date> dates;
}
