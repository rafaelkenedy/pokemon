package com.rafael.pokemon.config;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.rafael.pokemon.model.Pokemon;
import com.rafael.pokemon.repository.PokemonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

  private final PokemonRepository pokemonRepository;

  public DataSeeder(PokemonRepository pokemonRepository) {
    this.pokemonRepository = pokemonRepository;
  }

  @Override
  public void run(String... args) {
    if (pokemonRepository.count() > 151 || pokemonRepository.count() == 0) {
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
            String region = row[3];
            List<String> types = Arrays.asList(row[4].split(";"));

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
