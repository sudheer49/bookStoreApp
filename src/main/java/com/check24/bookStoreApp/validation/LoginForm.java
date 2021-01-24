package com.check24.bookStoreApp.validation;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Satya_Kolipaka
 * Form class for login page
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {
	
	@NotNull
	@NotEmpty(message="Email must not be empty")
	@Email
	@EmailCheck
	private String email;
	
	@NotNull
	@NotEmpty(message="Password must not be empty")
	private String password;
}
