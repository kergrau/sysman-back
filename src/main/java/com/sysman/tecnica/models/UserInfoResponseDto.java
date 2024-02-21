package com.sysman.tecnica.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponseDto implements Serializable {

	private static final long serialVersionUID = -4162925975058439186L;
	private String username;
	private String email;
	private String token;
}
