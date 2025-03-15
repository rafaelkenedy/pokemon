package com.rafael.pokemon.service;

import com.rafael.pokemon.model.Pokemon;
import com.rafael.pokemon.repository.PokemonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PokemonService {
  private final PokemonRepository pokemonRepository;

  public PokemonService(PokemonRepository pokemonRepository) {
    this.pokemonRepository = pokemonRepository;
  }

  public Page<Pokemon> getFilteredPokemons(
      String generation, String region, String type, int page) {
    int pageSize = 10;
    return pokemonRepository.findFiltered(generation, region, type, PageRequest.of(page, pageSize));
  }

  public Pokemon getRandomPokemon() {
    return pokemonRepository.findRandomPokemon();
  }
}
