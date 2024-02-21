package com.sysman.tecnica.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.sysman.tecnica.entities.User;
import com.sysman.tecnica.models.SignUpDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "roles", ignore = true)
	User toEntity(SignUpDto user);
}
