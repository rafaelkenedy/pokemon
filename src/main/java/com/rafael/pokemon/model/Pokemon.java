package com.rafael.pokemon.model;

import com.rafael.pokemon.model.enums.Type;
import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pokemons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {

  @Id private Long id;
  private String name;
  private String generation;
  private String region;

  @ElementCollection(targetClass = Type.class)
  @Enumerated(EnumType.STRING)
  private List<Type> types;
}
