package com.check24.bookStoreApp.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Satya Kolipaka Entity class for BOOKS table
 *
 */
@Entity
@Table(name = "BOOKS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

	@Id
	@Column(updatable = false, nullable = false)
	@SequenceGenerator(name = "BOOK_SEQ", sequenceName = "BOOK_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "BOOK_SEQ")
	private Integer id;

	@Column(nullable = false)
	private String bookId;

	@Column(nullable = false)
	private String name;

	@Column
	private String details;

	@Column
	private double price;

	@Column
	private String image;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<User> users = new HashSet<>();

	public Book(String bookId, String name, String details, double price, String image) {
		super();
		this.bookId = bookId;
		this.name = name;
		this.details = details;
		this.price = price;
		this.image = image;
	}

	@Override
	public String toString() {
		return "Book";
	}

	@Override
	public int hashCode() {
		return 1;
	}
}
