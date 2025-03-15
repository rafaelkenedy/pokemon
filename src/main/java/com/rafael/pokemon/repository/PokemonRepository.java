package com.rafael.pokemon.repository;

import com.rafael.pokemon.model.Pokemon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

  @Query(
      "SELECT p FROM Pokemon p WHERE "
          + "(:generation IS NULL OR p.generation = :generation) AND "
          + "(:region IS NULL OR p.region = :region) AND "
          + "(:type IS NULL OR :type MEMBER OF p.type)")
  Page<Pokemon> findFiltered(
      @Param("generation") String generation,
      @Param("region") String region,
      @Param("type") String type,
      Pageable pageable);

  @Query(value = "SELECT * FROM pokemons ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
  Pokemon findRandomPokemon();
}
