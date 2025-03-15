package com.rafael.pokemon.repository;

import com.rafael.pokemon.model.Pokemon;
import com.rafael.pokemon.model.enums.Type;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

  @Query(
      """
    SELECT p
    FROM Pokemon p
    WHERE
       (:generation IS NULL OR p.generation = :generation)
       AND (:region IS NULL OR p.region = :region)
       AND (
         :typeCount = 0
         OR (
           (SELECT COUNT(t3)
              FROM p.types t3
             WHERE t3 IN :types
           ) = :typeCount
         )
       )
""")
  Page<Pokemon> findByExactTypesAndFilters(
      @Param("generation") String generation,
      @Param("region") String region,
      @Param("types") List<Type> types,
      @Param("typeCount") int typeCount,
      Pageable pageable);

  @Query(
      """
    SELECT p
    FROM Pokemon p
    WHERE
        (:generation IS NULL OR p.generation = :generation)
        AND (:region IS NULL OR p.region = :region)
        AND (
            :#{#types == null or #types.isEmpty()} = true
            OR (
                (SELECT COUNT(t)
                 FROM p.types t
                 WHERE t IN :types
                ) = :#{#types.size()}
            )
        )
""")
  Page<Pokemon> findByTypesAndFilters(
      @Param("generation") String generation,
      @Param("region") String region,
      @Param("types") List<Type> types,
      Pageable pageable);

  @Query(value = "SELECT * FROM pokemons ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
  Pokemon findRandomPokemon();
}
