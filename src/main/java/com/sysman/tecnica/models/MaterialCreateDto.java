package com.sysman.tecnica.models;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialCreateDto implements Serializable {

	private static final long serialVersionUID = 8199900839733505777L;

	@NotBlank(message = "Field name cannot be empty or null")
	private String name;

	private String description;

	@NotBlank(message = "Field type cannot be empty or null")
	private String type;

	@NotBlank(message = "Field price cannot be empty or null")
	private String price;
	private LocalDate buyDate;
	private LocalDate sellDate;

	@NotBlank(message = "Field state cannot be empty or null")
	private String state;
	private Long cityId;
}
