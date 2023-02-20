package com.springmvc.validator;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.springmvc.domain.Book;

// Bean validation (JSR-380) + Spring validation(Validator interface)
public class BookValidator implements Validator {

	@Autowired
	private javax.validation.Validator beanValidator;

	private Set<Validator> springValidators;

	public BookValidator() {
		springValidators = new HashSet<>();
	}

	public void setSpringValidators(Set<Validator> springValidators) {
		this.springValidators = springValidators;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Book.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// Bean Validation
		Set<ConstraintViolation<Object>> violations = beanValidator.validate(target);
		for (ConstraintViolation<Object> violation : violations) {
			String propertyPath = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			errors.rejectValue(propertyPath, "", message);
		}

		// Spring Validation
		for (Validator validator : springValidators) {
			validator.validate(target, errors);
		}
	}

}
