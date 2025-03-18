package com.rafael.pokemon.service;

import com.rafael.pokemon.model.Pokemon;
import com.rafael.pokemon.repository.PokemonRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PokemonService {
  private final PokemonRepository pokemonRepository;

  public PokemonService(PokemonRepository pokemonRepository) {
    this.pokemonRepository = pokemonRepository;
  }

  public Page<Pokemon> getFilteredPokemons(
      String generation, String region, List<String> typesIds, int page) {
    Pageable pageable = PageRequest.of(page, 10);

    return pokemonRepository.findByTypesAndFilters(generation, region, typesIds, pageable);
  }

  public Pokemon getRandomPokemon() {
    return pokemonRepository.findRandomPokemon();
  }
}
