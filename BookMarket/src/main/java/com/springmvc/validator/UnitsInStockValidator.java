package com.springmvc.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.springmvc.domain.Book;

// Spring validation(Validator interface)
@Component
public class UnitsInStockValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// Book 클래스의 유효성 검사 여부를 위한 메서드
		return Book.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Book book = (Book) target;
		if (book.getUnitPrice() >= 10_000
				&& book.getUnitsInStock() > 99) {
			errors.rejectValue("unitsInStock", "UnitsInStockValidator.message");
		}
	}

}
