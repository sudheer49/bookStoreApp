package com.check24.bookStoreApp.validation;

import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

/**
 * 
 * @author Satya_Kolipaka
 * Custom validation to check email address
 */
@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
public @interface EmailCheck {
	String message() default "Email from test.com are invalid";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
