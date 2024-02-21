package com.sysman.tecnica.models;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto implements Serializable {

	private static final long serialVersionUID = 3280670510105337377L;
	@NotBlank(message = "Campo no puede ir vacio")
	private String email;
	@NotBlank(message = "Campo no puede ir vacio")
	private String username;
	@NotBlank(message = "Campo no puede ir vacio")
	private String password;

}
