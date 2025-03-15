package com.rafael.pokemon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponseDTO<T> {
  private final boolean success;
  private final String message;
  private final T data;
}
