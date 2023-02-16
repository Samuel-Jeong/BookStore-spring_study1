package com.springmvc.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@SuppressWarnings("serial")
@ControllerAdvice
public class CommonException extends RuntimeException {

	// CategoryException 이 들어올 수 있다.
	@ExceptionHandler(RuntimeException.class)
	private ModelAndView handleCommonError(Exception e) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("exception", e);
		modelAndView.setViewName("errorCommon");
		return modelAndView;
	}

}
