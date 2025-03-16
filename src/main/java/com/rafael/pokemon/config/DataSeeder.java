package com.rafael.pokemon.config;

import com.opencsv.CSVReader;
import com.rafael.pokemon.model.Pokemon;
import com.rafael.pokemon.model.Region;
import com.rafael.pokemon.model.enums.Type;
import com.rafael.pokemon.repository.PokemonRepository;
import com.rafael.pokemon.repository.RegionRepository;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

  private final PokemonRepository pokemonRepository;
  private final RegionRepository regionRepository;

  public DataSeeder(PokemonRepository pokemonRepository, RegionRepository regionRepository) {
    this.pokemonRepository = pokemonRepository;
    this.regionRepository = regionRepository;
  }

  @Override
  public void run(String... args) {
    if (pokemonRepository.count() > 1010 || pokemonRepository.count() == 0) {
      try {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("seed.csv");
        if (inputStream == null) {
          throw new RuntimeException("Arquivo seed.csv não encontrado no classpath!");
        }

        try (CSVReader reader =
            new CSVReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
          List<String[]> rows = reader.readAll();

          for (int i = 1; i < rows.size(); i++) {
            String[] row = rows.get(i);

            Long id = Long.parseLong(row[0]);
            String name = row[1];
            String generation = row[2];

            String regionId = row[3].toUpperCase();
            String regionName = row[3];

            Region region =
                regionRepository
                    .findById(regionId)
                    .orElseGet(() -> regionRepository.save(new Region(regionId, regionName)));

            List<Type> types =
                Arrays.stream(row[4].split(";"))
                    .map(String::toUpperCase)
                    .map(Type::valueOf)
                    .toList();

            Pokemon pokemon = new Pokemon(id, name, generation, region, types);
            pokemonRepository.save(pokemon);
          }
        }
      } catch (Exception e) {
        throw new RuntimeException("Erro ao ler o arquivo CSV ou salvar os dados no banco.", e);
      }
    }
  }
}
