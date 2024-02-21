package com.sysman.tecnica.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sysman.tecnica.entities.City;
import com.sysman.tecnica.entities.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

	List<Material> findByType(String type);

	List<Material> findByBuyDate(LocalDate buyDate);

	List<Material> findByCity(City city);

}
