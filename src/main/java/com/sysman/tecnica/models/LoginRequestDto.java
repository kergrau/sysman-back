package com.sysman.tecnica.models;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto implements Serializable {

	private static final long serialVersionUID = 6609664302338425162L;

	@NotBlank
	private String username;

	@NotBlank
	private String password;
}
