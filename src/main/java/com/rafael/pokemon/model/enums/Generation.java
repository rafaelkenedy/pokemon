package com.rafael.pokemon.model.enums;

import java.util.Arrays;

public enum Generation {
  GENERATION_I(1),
  GENERATION_II(2),
  GENERATION_III(3),
  GENERATION_IV(4),
  GENERATION_V(5),
  GENERATION_VI(6),
  GENERATION_VII(7),
  GENERATION_VIII(8),
  GENERATION_IX(9);

  private final int number;

  Generation(int generation) {
    this.number = generation;
  }

  public int getGeneration() {
    return number;
  }

  public static Generation fromNumber(int number) throws IllegalAccessException {
    return Arrays.stream(values())
        .filter(generation -> generation.number == number)
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Geração inválida: " + number));
  }
}
