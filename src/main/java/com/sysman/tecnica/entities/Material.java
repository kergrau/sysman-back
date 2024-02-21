package com.sysman.tecnica.entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "materials")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Material implements Serializable {

	private static final long serialVersionUID = -425510775346795368L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@Column
	private String description;

	@Column
	private String type;

	@Column
	private String price;

	@Column(name = "buy_date")
	private LocalDate buyDate;

	@Column(name = "sell_date")
	private LocalDate sellDate;

	@Column
	private String state;

	@ManyToOne
	@JoinColumn(name = "city_id", referencedColumnName = "id")
	private City city;

	public boolean dateValidation(LocalDate buyDate, LocalDate sellDate) {
		return buyDate.isAfter(sellDate);
	}

}
