package com.rafael.pokemon.config.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class Utils {

  private static final Map<Integer, String> ROMAN_MAP = new LinkedHashMap<>();

  static {
    ROMAN_MAP.put(1000, "M");
    ROMAN_MAP.put(900, "CM");
    ROMAN_MAP.put(500, "D");
    ROMAN_MAP.put(400, "CD");
    ROMAN_MAP.put(100, "C");
    ROMAN_MAP.put(90, "XC");
    ROMAN_MAP.put(50, "L");
    ROMAN_MAP.put(40, "XL");
    ROMAN_MAP.put(10, "X");
    ROMAN_MAP.put(9, "IX");
    ROMAN_MAP.put(5, "V");
    ROMAN_MAP.put(4, "IV");
    ROMAN_MAP.put(1, "I");
  }

  private Utils() {}

  public static String toRoman(Long number) {
    if (number <= 0) {
      throw new IllegalArgumentException("Número romano não definido para " + number);
    }

    StringBuilder sb = new StringBuilder();

    for (Map.Entry<Integer, String> entry : ROMAN_MAP.entrySet()) {
      while (number >= entry.getKey()) {
        sb.append(entry.getValue());
        number -= entry.getKey();
      }
    }

    return sb.toString();
  }
}
