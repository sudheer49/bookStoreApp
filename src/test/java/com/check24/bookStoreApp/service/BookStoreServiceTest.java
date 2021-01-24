package com.check24.bookStoreApp.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.check24.bookStoreApp.Dto.BookDetailsDto;
import com.check24.bookStoreApp.entity.Book;
import com.check24.bookStoreApp.entity.User;
import com.check24.bookStoreApp.exception.BooksNotFoundException;
import com.check24.bookStoreApp.repository.BookRepository;
import com.check24.bookStoreApp.repository.UserRepository;

import org.springframework.test.context.junit4.SpringRunner;

/**
 * 
 * @author Satya_Kolipaka
 * Unit test class for BookStoreService
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookStoreServiceTest {

	@Autowired
	private BookStoreService bookStoreService;
	
	@MockBean
	private BookRepository bookRepositoryMock;
	
	@MockBean
	private UserRepository userRepositoryMock;
	
	private Book buildBook() {
		Book book = new Book(1,"B1","Textbook A:  Unit testing basic principles","details",75,"image",null);
		return book;
	}

	private List<User> buildListUsers(List<Book> bookList) {
		 List<User> users = new ArrayList<>();
		 Set<Book> bookSet = new HashSet<>(bookList);
		 users.add(new User(1,"test1@check24.de",bookSet));
		 users.add(new User(2,"test2@check24.de",bookSet));
		 return users;
	}
	
	@Test
	public void getBooksSuccess() {
		List<Book> bookList = new ArrayList<>();
		bookList.add(buildBook());
		when(bookRepositoryMock.findAll()).thenReturn(bookList);
		List<Book> response =  bookStoreService.getBooks();
		Assert.assertEquals(response.get(0).getBookId(), "B1");
	}
	
	@Test(expected=BooksNotFoundException.class)
	public void getBooksFailure() {
		List<Book> bookList = new ArrayList<>();
		when(bookRepositoryMock.findAll()).thenReturn(bookList);
		List<Book> response =  bookStoreService.getBooks();
		Assert.assertEquals(response.get(0).getBookId(), "B1");
	}
	
	@Test
	public void fetchBookDetailsSuccess() throws Exception {
		Optional<Book> book = Optional.of(new Book(2,"B2","Textbook B:  Machine learning fundamentals","Book Details",124,"image",null));
		List<Book> bookList = new ArrayList<>();
		bookList.add(buildBook());
		List<User> users = buildListUsers(bookList);
		when(bookRepositoryMock.findById(2)).thenReturn(book);
		when(bookRepositoryMock.findAll()).thenReturn(bookList);
		when(userRepositoryMock.findAll()).thenReturn(users);
		BookDetailsDto response = bookStoreService.fetchBookDetails(2);
		Assert.assertEquals(response.getBookId(), "B2");
		
	}
}
