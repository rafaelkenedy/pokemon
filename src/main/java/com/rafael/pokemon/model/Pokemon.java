package com.rafael.pokemon.model;

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

  @ManyToOne
  @JoinColumn(name = "generation_id")
  private Generation generation;

  @ManyToOne
  @JoinColumn(name = "region_id")
  private Region region;

  @ElementCollection(targetClass = Type.class)
  @Enumerated(EnumType.STRING)
  private List<Type> types;
}
