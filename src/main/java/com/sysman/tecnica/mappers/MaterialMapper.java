package com.sysman.tecnica.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.sysman.tecnica.entities.Material;
import com.sysman.tecnica.models.MaterialCreateDto;
import com.sysman.tecnica.models.MaterialViewDto;

@Mapper(componentModel = "spring")
public interface MaterialMapper {

	MaterialMapper INSTANCE = Mappers.getMapper(MaterialMapper.class);

	MaterialViewDto toViewDto(Material material);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "city", ignore = true)
	Material toEntity(MaterialCreateDto material);

}
