package com.miso.eksamensprojektf23.enums;

public enum AppointmentType {

  TELEFON_SAMTALE,
  AFLYSNING,
  UNDERVISNING,
  ADL,
  MOBILITY;

  public String displayname() {
    if (this == TELEFON_SAMTALE)
      return "Telefon Samtale";
    else if (this == AFLYSNING) {
      return "Aflysning";
    } else if (this == UNDERVISNING) {
      return "Undervisning";
    } else if (this == ADL) {
      return "ADL";
    } else {
      return "Mobility";
    }
  }
}
