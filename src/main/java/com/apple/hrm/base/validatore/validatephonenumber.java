package com.apple.hrm.base.validatore;

import org.springframework.beans.factory.annotation.Configurable;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Configurable
public class validatephonenumber implements ConstraintValidator<Validphonenumber, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return false;
	}

}
