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
 * @author Satya Kolipaka Entity class for users table
 * 
 */

@Entity
@Table(name = "USERS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@Column
	@SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "USER_SEQ")
	private Integer id;

	@Column
	private String userName;

	@ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
	private Set<Book> books = new HashSet<>();

	@Override
	public String toString() {
		return "User";
	}

	@Override
	public int hashCode() {
		return 1;
	}
}
