package com.rafael.pokemon.exception;

import com.rafael.pokemon.dto.ApiResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(PokemonNotFoundException.class)
  public ResponseEntity<ApiResponseDTO<Void>> handlePokemonNotFoundException(
      PokemonNotFoundException ex) {
    ApiResponseDTO<Void> response = new ApiResponseDTO<>(false, ex.getMessage(), null);
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponseDTO<Void>> handleGeneralException(Exception ex) {
    ApiResponseDTO<Void> response = new ApiResponseDTO<>(false, "Erro interno no servidor.", null);
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
