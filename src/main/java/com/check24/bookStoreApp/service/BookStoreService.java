package com.check24.bookStoreApp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.check24.bookStoreApp.Dto.BookDetailsDto;
import com.check24.bookStoreApp.Dto.BookDto;
import com.check24.bookStoreApp.entity.Book;
import com.check24.bookStoreApp.entity.User;
import com.check24.bookStoreApp.exception.BooksNotFoundException;
import com.check24.bookStoreApp.repository.BookRepository;
import com.check24.bookStoreApp.repository.UserRepository;

/**
 * 
 * @author Satya Kolipaka 
 * Service class for Book store application
 *
 */
@Service
public class BookStoreService {

	private static Logger logger = LoggerFactory.getLogger(BookStoreService.class);
	
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private UserRepository userRepository;

	/**
	 * @return
	 * @throws BooksNotFoundException
	 */
	public List<Book> getBooks() throws BooksNotFoundException{
		logger.info("Fetching all Books ");
		List<Book> books = bookRepository.findAll();
		if(books.isEmpty()) {
			logger.error("There are no books in store");
			throw new BooksNotFoundException("Currently there are no books in store");
		}
		return books;
	}

	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public BookDetailsDto fetchBookDetails(Integer id) throws Exception{
		logger.info("Fetching Book Details of BookId:"+id);
		
		Optional<Book> book = bookRepository.findById(id);
		BookDetailsDto bookDetailsDto = null;
		if (book.isPresent()) {
			Book bookData = book.get();
			List<Book> suggestedBooks = getBooks().stream().filter(s -> suggestedBooks(id, s.getId()))
					.collect(Collectors.toList());
			bookDetailsDto = new BookDetailsDto(bookData.getId(), bookData.getBookId(), bookData.getName(),
					bookData.getDetails(), bookData.getPrice(), bookData.getImage(), suggestedBooks);
			return bookDetailsDto;
		}
		else {
			logger.error("Book details not found");
			throw new BooksNotFoundException("Book details not found");
		}

		
	}

	/**
	 * Used for implementing the cosine-similarity function.
	 * @param selectedBookId
	 * @param compareBookId
	 * @return
	 */
	public boolean suggestedBooks(Integer selectedBookId, Integer compareBookId) {

		if (selectedBookId == compareBookId) {
			return false;
		}
		List<User> users = userRepository.findAll();
		int a = 0;
		int b = 0;
		int c = 0;
		for (User u : users) {
			List<Integer> bookIds = u.getBooks().stream().map(m -> m.getId()).collect(Collectors.toList());
			int i = bookIds.contains(selectedBookId) ? 1 : 0;
			int j = bookIds.contains(compareBookId) ? 1 : 0;
			a = a + (i * j);
			b = b + (i * i);
			c = c + (j * j);
		}
		double result = (a / (Math.sqrt(b) * Math.sqrt(c)));
		return result > 0;
	}
	
	public Book addBook(BookDto bookDto) {
		Book book = new Book(bookDto.getBookId(), bookDto.getName(), bookDto.getDetails(), bookDto.getPrice(),
				bookDto.getImage());
		return bookRepository.save(book);
	}

}
