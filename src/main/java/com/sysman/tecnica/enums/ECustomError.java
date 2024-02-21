package com.sysman.tecnica.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ECustomError {

	UNEXPECTED_ERROR("UNEXPECTED_ERROR", "An unexpected error ocurred"),
	PERSON_NOT_FOUND("PERSON_NOT_FOUND", "Person not found"),
	CITY_NOT_FOUND("CITY_NOT_FOUND", "Not found materials in city"),
	MATERIAL_NOT_FOUND("MATERIAL_NOT_FOUND", "Material not found"),
	MATERIAL_TYPE_NOT_FOUND("MATERIAL_TYPE_NOT_FOUND", "Material type not found"),
	BUY_DATE_NOT_FOUND("BUY_DATE_NOT_FOUND", " Material buy date not found"),
	WRONG_PASSWORD("WRONG_PASSWORD", "Wrong password, try again"),
	PASSWORD_CHANGE_FAILED("PASSWORD_CHANGE_FAILED", "The password change failed"),
	EMAIL_CHANGE_FAILED("EMAIL_CHANGE_FAILED", "The E-mail change failed"),
	EMAIL_TAKEN("EMAIL TAKEN", "This email has been taken"),
	USERNAME_TAKEN("USERNAME TAKEN", "This username has been taken"),
	STATUS_CHANGE_FAILED("STATUS_CHANGE_FAILED", "The status change failed"),
	ACCESS_DENIED("ACCESS_DENIED", "You no have permissions for this feature"),
	FIELDS_EMPTY_OR_NULL("FIELDS_EMPTY_OR_NULL", "One or more fields are empty or null"),
	INVALID_DATES("INVALID_DATES", "Sell date cannot be major than buy date");

	private final String errorCode;
	private final String message;

}
