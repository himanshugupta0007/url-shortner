/**
 * 
 */
package com.mysocialpal.urlshortner.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This exception will be raised if request has invalid attributes
 * 
 * @author Himanshu.Gupta
 *
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class URLShortnerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String errorCode;
	private String errorMessage;
	private HttpStatus httpStatus;

}
