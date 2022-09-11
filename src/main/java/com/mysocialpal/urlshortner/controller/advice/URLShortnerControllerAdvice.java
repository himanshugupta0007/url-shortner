/**
 * 
 */
package com.mysocialpal.urlshortner.controller.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mysocialpal.urlshortner.common.ResponseEnvelop;
import com.mysocialpal.urlshortner.exception.URLShortnerException;

/**
 * This class handles all the exception scenario and respond back with relevant
 * error response accordingly
 * 
 * @author Himanshu.Gupta
 * @version 1.0.0
 */
@SuppressWarnings("rawtypes")
@RestControllerAdvice
public class URLShortnerControllerAdvice {

	/**
	 * Handles the {@link URLShortnerException}
	 * 
	 * @param urlShortnerException
	 * @return
	 */
	@ExceptionHandler(URLShortnerException.class)
	public ResponseEntity<ResponseEnvelop> handleInvalidURLInputException(
			final URLShortnerException urlShortnerException) {
		ResponseEnvelop response = ResponseEnvelop.error(urlShortnerException.getErrorCode(),
				urlShortnerException.getErrorMessage());
		return ResponseEntity.status(urlShortnerException.getHttpStatus()).body(response);
	}
}
