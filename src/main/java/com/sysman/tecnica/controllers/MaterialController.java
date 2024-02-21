package com.sysman.tecnica.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sysman.tecnica.models.MaterialCreateDto;
import com.sysman.tecnica.models.MaterialViewDto;
import com.sysman.tecnica.services.MaterialService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/materials")
public class MaterialController {

	@Autowired
	private MaterialService materialService;

	@GetMapping("/all")
	@Operation(description = "Return all materials", summary = "Return all available material")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Return all", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MaterialViewDto.class)) }),
			@ApiResponse(responseCode = "500", description = "Error not exepected", content = {
					@Content(schema = @Schema()) }) })
	public ResponseEntity<?> all() {
		List<MaterialViewDto> lMaterial = materialService.allMaterials();
		return new ResponseEntity<List<MaterialViewDto>>(lMaterial, HttpStatus.OK);
	}

	@GetMapping("/by-type")
	@Operation(description = "Return materials by type", summary = "Return materials by type")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Return by type", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MaterialViewDto.class)) }),
			@ApiResponse(responseCode = "500", description = "Error not exepected", content = {
					@Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "404", description = "Material type not found") })
	public ResponseEntity<?> byType(@RequestParam String type) {
		List<MaterialViewDto> lMaterial = materialService.materialsByType(type);
		return new ResponseEntity<List<MaterialViewDto>>(lMaterial, HttpStatus.OK);
	}

	@GetMapping("/by-buy-date")
	@Operation(description = "Return materials by buy date", summary = "Return materials by buy date")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Return by buy date", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MaterialViewDto.class)) }),
			@ApiResponse(responseCode = "500", description = "Error not exepected", content = {
					@Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "404", description = "Material buy date not found") })
	public ResponseEntity<?> byBuyDate(@RequestParam LocalDate buyDate) {
		List<MaterialViewDto> lMaterial = materialService.materialsByBuyDate(buyDate);
		return new ResponseEntity<List<MaterialViewDto>>(lMaterial, HttpStatus.OK);
	}

	@GetMapping("/by-city")
	@Operation(description = "Return materials by city", summary = "Return materials by city")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Return by city", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = MaterialViewDto.class)) }),
			@ApiResponse(responseCode = "500", description = "Error not exepected", content = {
					@Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "404", description = "Not found materials in city") })
	public ResponseEntity<?> byCity(@RequestParam Long idCity) {
		List<MaterialViewDto> lMaterial = materialService.materialsByCity(idCity);
		return new ResponseEntity<List<MaterialViewDto>>(lMaterial, HttpStatus.OK);
	}

	@PostMapping("/create")
	@Operation(description = "Save a material", summary = "Save material")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Material created", content = {
					@Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", description = "Error not exepected", content = {
					@Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "400", description = "Data is malformed") })
	public ResponseEntity<?> create(@Valid @RequestBody MaterialCreateDto material) {
		materialService.create(material);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/update")
	@Operation(description = "Update material", summary = "Update material by id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Update material"),
			@ApiResponse(responseCode = "404", description = "Material not found"),
			@ApiResponse(responseCode = "500", description = "Error not exepected") })
	public ResponseEntity<?> update(@RequestParam Long id, @Valid @RequestBody MaterialCreateDto material) {
		materialService.update(id, material);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
