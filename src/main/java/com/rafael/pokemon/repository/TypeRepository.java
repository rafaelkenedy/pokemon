package com.rafael.pokemon.repository;

import com.rafael.pokemon.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, String> {}
