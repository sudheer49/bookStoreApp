package com.check24.bookStoreApp.Dto;

import lombok.Data;

/**
 * @author Satya Kolipaka Dto class for Book Entity
 */
@Data
public class BookDto {

	private String bookId;

	private String name;

	private String details;

	private double price;

	private String image;
}
