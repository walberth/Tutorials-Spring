package com.ps.springmvc.psbankapp.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PSCodeConstraintValidator implements ConstraintValidator<PSCode, String>{
	
	public String codePrefix;
	
	@Override
	public void initialize(PSCode code) {
		codePrefix = code.value();
	}

	@Override
	public boolean isValid(String code, ConstraintValidatorContext arg1) {
		boolean result;
		
		if( code != null )
			result = code.startsWith(codePrefix);
		else
			result = true;
		
		return result;
	}
}
