package com.springmvc.util;

public class Util {

	private static final String LOG_PREFIX_FORMAT = "{}|{} - ";

	public static String getLogPrefix(Class<?> clazz) {
		return String.format(LOG_PREFIX_FORMAT, clazz.getName(), getMethodName());
	}

	private static String getMethodName() {
		return Thread.currentThread().getStackTrace()[1].getMethodName();
	}

}
