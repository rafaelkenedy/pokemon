package com.rafael.pokemon.exception;

public class PokemonNotFoundException extends RuntimeException {
  public PokemonNotFoundException(String msg) {
    super(msg);
  }
}
