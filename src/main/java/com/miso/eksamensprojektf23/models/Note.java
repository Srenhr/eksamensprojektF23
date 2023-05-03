package com.miso.eksamensprojektf23.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "note_type",
    discriminatorType = DiscriminatorType.INTEGER)
@Table(name = "notes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString /*Remember to add ToString.Exclude to lazy fields, https://www.jpa-buddy.com/blog/lombok-and-jpa-what-may-go-wrong/*/
public abstract class Note {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "note_id")
  private Long noteId;

  private String title;

  private String noteText;
}
