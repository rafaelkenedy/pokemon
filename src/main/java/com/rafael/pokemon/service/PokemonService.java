package com.rafael.pokemon.service;

import com.rafael.pokemon.model.Pokemon;
import com.rafael.pokemon.model.enums.Type;
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
      String generation, String region, List<Type> types, int page) {
    Pageable pageable = PageRequest.of(page, 10);

    // Se nao tem types, so filtra generation/region
    //    if (types == null || types.isEmpty()) {
    //      return pokemonRepository.findAll(
    //          Specification.where(PokemonSpecs.withGeneration(generation))
    //              .and(PokemonSpecs.withRegion(region)),
    //          pageable);
    //    }

    // Se tiver 1 tipo => buscar "Pokémons que incluam esse tipo" (JOIN + t IN ...)
    //    if (types.size() == 1) {
    //      // Aí você faz outra query que pega todos com aquele tipo, sem exigir exclusividade
    //      return pokemonRepository.findBySingleTypeAndFilters(
    //              generation,
    //              region,
    //              types.get(0), // pega o único
    //              pageable
    //      );
    //    }

    //    int typeSize = (types != null) ? types.size() : 0;
    //    // Se tiver 2 ou mais => buscar "exatamente esses"
    //    return pokemonRepository.findByExactTypesAndFilters(
    //        generation, region, types,
    //            typeSize,
    //            pageable);

    int typeSize = (types != null) ? types.size() : 0;
    // Se tiver 2 ou mais => buscar "exatamente esses"
    return pokemonRepository.findByTypesAndFilters(
        generation, region, types, pageable);
  }

  public Pokemon getRandomPokemon() {
    return pokemonRepository.findRandomPokemon();
  }
}
