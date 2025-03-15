package com.rafael.pokemon.model.enums;

public enum Region {
  KANTO,
  JOHTO,
  HOENN,
  SINNOH,
  UNOVA,
  KALOS,
  ALOLA,
  GALAR,
  PALDEA;

  public static Region fromString(String name) {
    return Region.valueOf(name.trim().toUpperCase());
  }
}
