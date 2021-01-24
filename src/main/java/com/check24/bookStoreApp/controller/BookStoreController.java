package com.check24.bookStoreApp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.check24.bookStoreApp.Dto.BookDto;
import com.check24.bookStoreApp.entity.Book;
import com.check24.bookStoreApp.service.BookStoreService;
import com.check24.bookStoreApp.validation.LoginForm;

/**
 * 
 * @author Satya Kolipaka 
 * Controller class which expose end points for book store application
 *
 */
@Controller
@RequestMapping("/task")
public class BookStoreController {
	private static final String LOGIN = "login";
	private static final String REDIRECT_LOGIN = "redirect:/task/books";
	private static final String BOOKS="books";
	private static final String DETAILS="details";
	

	@Autowired
	private BookStoreService bookStoreService;

	/**
	 * @param loginForm
	 * @return
	 */
	@RequestMapping("/login")
	public String loginPage(LoginForm loginForm) {
		return LOGIN;
	}
	
	/**
	 * @param loginForm
	 * @param bindingResult
	 * @return 
	 */
	@PostMapping("/login")
	public String loginValidation(@Valid LoginForm loginForm,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return LOGIN;
		}
		return REDIRECT_LOGIN;
	}

	/**
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/books")
	public String getBooks(Model model) throws Exception {
		model.addAttribute("bookList", bookStoreService.getBooks());
		return BOOKS;
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/details/{id}")
	public String bookDetails(@PathVariable Integer id, Model model) throws Exception {
		model.addAttribute("bookDetails", bookStoreService.fetchBookDetails(id));
		return DETAILS;
	}

	/**
	 * @param bookDto
	 * @return
	 */
	@ResponseBody
	@PostMapping("/books")
	public Book addBook(@RequestBody final BookDto bookDto) {
		return bookStoreService.addBook(bookDto);
	}
}
