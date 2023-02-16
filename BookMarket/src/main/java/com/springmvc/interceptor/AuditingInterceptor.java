package com.springmvc.interceptor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuditingInterceptor implements HandlerInterceptor {

	private static final Logger log = LoggerFactory.getLogger(AuditingInterceptor.class);
	
	private String user;
	private String bookId;

	
	@Override
	public boolean preHandle(HttpServletRequest request, 
							HttpServletResponse response, 
							Object handler) 
									throws Exception {
		
		// 특정 URI 에 해당하는 요청만 Intercept
		if (request.getRequestURI().endsWith("books/add") 
				&& request.getMethod().equals("POST")) {
			user = request.getRemoteUser();
			bookId = request.getParameterValues("bookId")[0];
		}
		
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, 
								HttpServletResponse response, 
								Object handler, 
								Exception exception) 
									throws Exception {
		
		// 특정 URI 에 해당하는 요청만 Intercept
		if (request.getRequestURI().endsWith("books/add")) {
			log.warn("신규 등록 도서 : {} / 접근자 : {} / 접근 시각 : {}", 
						bookId, user, getCurrentTime()
			);
		}		
	}
	
	private Object getCurrentTime() {
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		return formatter.format(calendar.getTime());
	}
	
}
