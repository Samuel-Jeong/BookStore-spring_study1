package com.springmvc.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.springmvc.domain.Book;
import com.springmvc.exception.BookIdException;
import com.springmvc.service.BookService;

// Bean validation (JSR-380)
public class BookIdValidator implements ConstraintValidator<BookId, String> {

	@Autowired
	private BookService bookService;
	
	public void initialize(BookId constraintAnnotation) {
		
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Book book;
		
		try {
			book = bookService.getBookById(value);
		} catch (BookIdException e) {
			return true;
		}
		
		// 이미 존재하면 유효성 검사 실패로 반환
		if (book != null) {
			return false;
		}
		
		return true;
	}

}
