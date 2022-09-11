/**
 *
 */
package com.mysocialpal.urlshortner.validator;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.http.HttpStatus;

import com.mysocialpal.urlshortner.dto.URLShortnerRequest;
import com.mysocialpal.urlshortner.exception.URLErrorCode;
import com.mysocialpal.urlshortner.exception.URLShortnerException;

/**
 * This implementation is responsible to validate the URL Shorten Request
 * parameters
 *
 * @author Himanshu.Gupta
 * @since
 */
public class URLRequestValidationImpl implements ConstraintValidator<URLRequestValidator, URLShortnerRequest> {

    @Override
    public boolean isValid(URLShortnerRequest request, ConstraintValidatorContext context) {
        if (null == request) {
            throwException(URLErrorCode.URL_REQUEST_MISSING.getErrorCode(),
                    URLErrorCode.URL_REQUEST_MISSING.getErrorMessage());
        }

        validateURL(request.getUrl());
        validateAlias(request.getAlias());

        return true;
    }

    /**
     * Validate if Alias is of correct length and also if the same alias has already
     * been used
     *
     * @param alias
     */
    private void validateAlias(String alias) {
        if (alias != null && !alias.isEmpty()) {
            if (alias.length() < 5 || alias.length() > 8) {
                throwException(URLErrorCode.INVALID_URL_ALIAS.getErrorCode(),
                        URLErrorCode.INVALID_URL_ALIAS.getErrorMessage());
            }
        }
    }

    /**
     * This method validates the URL and it's format
     *
     * @param url
     */
    private void validateURL(String url) {
        if (url != null && !url.isEmpty()) {
            try {
                URL validateUrl = new URL(url);
                validateUrl.toURI();
            } catch (MalformedURLException | URISyntaxException e) {
                throwException(URLErrorCode.INVALID_URL.getErrorCode(), URLErrorCode.INVALID_URL.getErrorMessage());
            }
        } else {
            throwException(URLErrorCode.URL_MISSING.getErrorCode(), URLErrorCode.URL_MISSING.getErrorMessage());
        }
    }

    /**
     * This method throws the invalid Exception
     *
     * @param errorCode
     * @param errorMessage
     */
    private void throwException(String errorCode, String errorMessage) {
        throw new URLShortnerException(errorCode, errorMessage, HttpStatus.BAD_REQUEST);
    }
}
