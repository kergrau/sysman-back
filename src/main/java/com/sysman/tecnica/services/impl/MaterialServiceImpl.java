package com.sysman.tecnica.services.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sysman.tecnica.entities.City;
import com.sysman.tecnica.entities.Material;
import com.sysman.tecnica.enums.ECustomError;
import com.sysman.tecnica.exceptions.CustomException;
import com.sysman.tecnica.mappers.MaterialMapper;
import com.sysman.tecnica.models.MaterialCreateDto;
import com.sysman.tecnica.models.MaterialViewDto;
import com.sysman.tecnica.repositories.CityRepository;
import com.sysman.tecnica.repositories.MaterialRepository;
import com.sysman.tecnica.services.MaterialService;

@Service
public class MaterialServiceImpl implements MaterialService {

	@Autowired
	private MaterialRepository materialRepository;

	@Autowired
	private MaterialMapper materialMapper;

	@Autowired
	private CityRepository cityRepository;

	@Override
	public List<MaterialViewDto> allMaterials() {
		List<Material> lMaterial = materialRepository.findAll();
		return toMaterialViewDto(lMaterial);
	}

	@Override
	public List<MaterialViewDto> materialsByType(String type) {
		List<Material> lMaterial = materialRepository.findByType(type);
		if (lMaterial.isEmpty()) {
			throw new CustomException(ECustomError.MATERIAL_TYPE_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
		return toMaterialViewDto(lMaterial);
	}

	@Override
	public List<MaterialViewDto> materialsByBuyDate(LocalDate buyDate) {
		List<Material> lMaterial = materialRepository.findByBuyDate(buyDate);
		if (lMaterial.isEmpty()) {
			throw new CustomException(ECustomError.BUY_DATE_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
		return toMaterialViewDto(lMaterial);
	}

	@Override
	public List<MaterialViewDto> materialsByCity(Long idCity) {
		City city = new City();
		city.setId(idCity);
		List<Material> lMaterial = materialRepository.findByCity(city);
		if (lMaterial.isEmpty()) {
			throw new CustomException(ECustomError.CITY_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
		return toMaterialViewDto(lMaterial);
	}

	@Override
	public void create(MaterialCreateDto materialDto) {
		Material material = materialMapper.toEntity(materialDto);
		City city = cityRepository.findById(materialDto.getCityId())
				.orElseThrow(() -> new CustomException(ECustomError.CITY_NOT_FOUND, HttpStatus.BAD_REQUEST));
		material.setCity(city);
		if (material.dateValidation(materialDto.getBuyDate(), materialDto.getSellDate())) {
			throw new CustomException(ECustomError.INVALID_DATES, HttpStatus.BAD_REQUEST);
		}
		materialRepository.save(material);
	}

	@Override
	public void update(Long id, MaterialCreateDto materialDto) {
		Material material = materialRepository.findById(id)
				.orElseThrow(() -> new CustomException(ECustomError.MATERIAL_NOT_FOUND, HttpStatus.NOT_FOUND));
		material.setName(materialDto.getName());
		material.setDescription(materialDto.getDescription());
		material.setPrice(material.getPrice());
		material.setSellDate(material.getSellDate());
		material.setState(material.getState());
		material.setType(materialDto.getType());
		material.setBuyDate(materialDto.getBuyDate());
		if (material.dateValidation(materialDto.getBuyDate(), materialDto.getSellDate())) {
			throw new CustomException(ECustomError.INVALID_DATES, HttpStatus.BAD_REQUEST);
		}
		materialRepository.save(material);
	}

	private List<MaterialViewDto> toMaterialViewDto(List<Material> lMaterial) {
		return lMaterial.stream().map(material -> materialMapper.toViewDto(material)).toList();
	}

}
