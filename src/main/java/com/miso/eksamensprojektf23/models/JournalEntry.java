package com.miso.eksamensprojektf23.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "patient_notes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString /*Remember to add ToString.Exclude to lazy fields, https://www.jpa-buddy.com/blog/lombok-and-jpa-what-may-go-wrong/*/
public class JournalEntry {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "journal_entry_id")
  private Long journalEntryId;

  @ManyToOne
  @JoinColumn(name = "patient_id")
  private Patient patient;
}
