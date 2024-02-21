package com.sysman.tecnica.services;

import java.time.LocalDate;
import java.util.List;

import com.sysman.tecnica.models.MaterialCreateDto;
import com.sysman.tecnica.models.MaterialViewDto;

public interface MaterialService {

	public List<MaterialViewDto> allMaterials();

	public List<MaterialViewDto> materialsByType(String type);

	public List<MaterialViewDto> materialsByBuyDate(LocalDate buyDate);

	public List<MaterialViewDto> materialsByCity(Long idCity);

	public void create(MaterialCreateDto material);

	public void update(Long id, MaterialCreateDto material);
}
