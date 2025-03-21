package com.rafael.pokemon.controller;

import com.rafael.pokemon.dto.ApiResponseDTO;
import com.rafael.pokemon.dto.PageResponseDTO;
import com.rafael.pokemon.exception.PokemonNotFoundException;
import com.rafael.pokemon.model.Pokemon;
import com.rafael.pokemon.service.PokemonService;
import java.util.Collections;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pokemons")
public class PokemonController {
  private final PokemonService pokemonService;

  public PokemonController(PokemonService pokemonService) {
    this.pokemonService = pokemonService;
  }

  @GetMapping
  public ApiResponseDTO<PageResponseDTO<Pokemon>> getPokemons(
      @RequestParam(required = false) String generation,
      @RequestParam(required = false) String region,
      @RequestParam(value = "types", required = false) List<String> types,
      @RequestParam(defaultValue = "0") int page) {

    try {
      if (page < 0) {
        throw new IllegalArgumentException("O número da página não pode ser negativo.");
      }

      if (types == null) {
        types = Collections.emptyList();
      }

      Page<Pokemon> pokemonPage =
          pokemonService.getFilteredPokemons(generation, region, types, page);

      if (pokemonPage.isEmpty()) {
        throw new PokemonNotFoundException("Nenhum Pokémon encontrado para os filtros aplicados.");
      }

      return new ApiResponseDTO<>(
          true, "Lista de Pokémons recuperada com sucesso!", new PageResponseDTO<>(pokemonPage));

    } catch (PokemonNotFoundException e) {
      return new ApiResponseDTO<>(false, e.getMessage(), null);
    } catch (IllegalArgumentException e) {
      return new ApiResponseDTO<>(false, "Parâmetro inválido: " + e.getMessage(), null);
    } catch (Exception e) {
       return new ApiResponseDTO<>(false, "Erro ao buscar Pokémons.", null);
    }
  }

  @GetMapping("/random")
  public ApiResponseDTO<Pokemon> getRandomPokemon() {
    try {
      Pokemon randomPokemon = pokemonService.getRandomPokemon();
      if (randomPokemon == null) {
        throw new PokemonNotFoundException("Nenhum Pokémon encontrado.");
      }
      return new ApiResponseDTO<>(true, "Quem é esse Pokémon?", randomPokemon);
    } catch (PokemonNotFoundException e) {
      return new ApiResponseDTO<>(false, e.getMessage(), null);
    } catch (Exception e) {
      return new ApiResponseDTO<>(false, "Ocorreu um erro inesperado ao buscar um Pokémon.", null);
    }
  }
}
