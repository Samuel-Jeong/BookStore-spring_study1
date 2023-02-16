package com.springmvc.interceptor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MonitoringInterceptor implements HandlerInterceptor {

	private final ThreadLocal<StopWatch> stopWatchLocal;
	
	private static final Logger log = LoggerFactory.getLogger(MonitoringInterceptor.class);
	
	public MonitoringInterceptor() {
		stopWatchLocal = new ThreadLocal<>();
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, 
							HttpServletResponse response, 
							Object handler) 
									throws Exception {
		StopWatch stopWatch = new StopWatch(handler.toString());
		stopWatch.start(handler.toString());
		stopWatchLocal.set(stopWatch);
		
		log.info("{} | 요청 처리 시작 시각 : {}", getURLPath(request), getCurrentTime());
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, 
							HttpServletResponse response, 
							Object handler, 
							ModelAndView modelAndView)
									throws Exception {
		log.info("\t{} | 요청 처리 종료 시각 : {}", getURLPath(request), getCurrentTime());
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, 
								HttpServletResponse response, 
								Object handler, 
								Exception exception) 
									throws Exception {
		StopWatch stopWatch = stopWatchLocal.get();
		stopWatch.stop();
		log.info("\t\t{} | 요청 처리 소요 시각 : {} ms", getURLPath(request), stopWatch.getTotalTimeMillis());
		stopWatchLocal.set(null);
		log.info("\tEND < {} >", getURLPath(request));
	}

	private Object getURLPath(HttpServletRequest request) {
		String currentPath = request.getRequestURI();
		String queryString = request.getQueryString();
		
		queryString = queryString == null ? "" : "?" + queryString;
		
		return currentPath + queryString;
	}
	
	private Object getCurrentTime() {
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		return formatter.format(calendar.getTime());
	}
	
}
