package com.rafael.pokemon.repository;

import com.rafael.pokemon.model.Generation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenerationRepository extends JpaRepository<Generation, Long> {}
