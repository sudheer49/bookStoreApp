package com.check24.bookStoreApp.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
/**
 * This class in a controller advise that is executed when the exception
 * is thrown in controller classes. It also helps to convert the exception into
 * and Errors to be returned with appropriate error message
 * 
 * @author Satya Kolipaka
 *
 */
@ControllerAdvice
public class BookStoreControllerAdvice {

	@ExceptionHandler(Exception.class)
	public ModelAndView handleExecption(Exception ex) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error");
		modelAndView.addObject("message",ex.getMessage());
		
		return modelAndView;
	}
}
