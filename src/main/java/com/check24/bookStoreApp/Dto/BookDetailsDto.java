package com.check24.bookStoreApp.Dto;

import java.util.List;

import com.check24.bookStoreApp.entity.Book;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Satya Kolipaka Dto class for Book Details information
 */
@Data
@AllArgsConstructor
public class BookDetailsDto {

	private Integer id;

	private String bookId;

	private String name;

	private String details;

	private double price;

	private String image;

	private List<Book> interstedBooks;
}
