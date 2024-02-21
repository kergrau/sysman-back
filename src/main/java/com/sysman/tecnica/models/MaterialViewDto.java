package com.sysman.tecnica.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialViewDto implements Serializable {

	private static final long serialVersionUID = 3768066094150058443L;
	private String name;
	private String description;
	private String type;
	private String price;
	private String state;

}
