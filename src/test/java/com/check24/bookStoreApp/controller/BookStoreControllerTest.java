package com.check24.bookStoreApp.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.check24.bookStoreApp.service.BookStoreService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


import org.junit.Before;

/**
 * 
 * @author Satya_Kolipaka
 * Unit test class for BookStoreController
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BookStoreControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private BookStoreController bookStoreController;

	@MockBean
	private BookStoreService bookStoreServiceMock;
	
	@Mock
    private Model model;

	@Before
	public void setUp() throws Exception {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setSuffix(".html");

		this.mockMvc = MockMvcBuilders.standaloneSetup(bookStoreController).setViewResolvers(viewResolver)
				.setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
	}

//	private Book buildBook() {
//		Book book = new Book(1,"B1","Textbook A:  Unit testing basic principles","details",75,"image",null);
//		return book;
//	}
	@Test
	public void validateLoginFormSuccess() throws Exception {
		this.mockMvc
				.perform(post("/task/login").param("email", "test1@check24.de").param("password", "test123"))
				.andExpect(view().name("redirect:/task/books")).andExpect(status().isFound());
	}

	@Test
	public void validateLoginFormInValidEmail() throws Exception {
		this.mockMvc
				.perform(post("/task/login").param("email", "test1@test.com").param("password", "test123"))
				.andExpect(model().attributeHasFieldErrors("loginForm", "email")).andExpect(view().name("login"))
				.andExpect(status().isOk());
	}
	
//	@Test
//	public void validateGetBooksSuccess() throws Exception {
//		List<Book> bookList = new ArrayList<>();
//		bookList.add(buildBook());
//		when(bookStoreServiceMock.getBooks()).thenReturn(bookList);		
//		this.mockMvc.perform(get("/task/books").)
//				.andExpect(model().attribute("bookList", bookList))
//				.andExpect(view().name("books"));
//		
//	}

}
