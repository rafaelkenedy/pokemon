package com.rafael.pokemon.repository;

import com.rafael.pokemon.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, String> {

}
