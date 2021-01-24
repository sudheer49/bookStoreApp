package com.check24.bookStoreApp.exception;

/**
 * 
 * @author Satya_Kolipaka
 * Custom validation for Books not found
 */
public class BooksNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public BooksNotFoundException(String message) {
		super(message);
	}
}
