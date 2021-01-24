package com.check24.bookStoreApp.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
/**
 * 
 * @author Satya_Kolipaka
 * Validation class to check email
 */
public class EmailValidator implements ConstraintValidator<EmailCheck, String> {

	 @Override
	    public boolean isValid(String value, ConstraintValidatorContext context) {
	        return !value.endsWith("test.com");

	    }
}
