/**
 *
 */
package com.mysocialpal.urlshortner.exception;

import lombok.Getter;

/**
 * This includes the collection of Error codes for URL Shortner Service
 *
 * @author Himanshu.Gupta
 */
@Getter
public enum URLErrorCode {

    URL_REQUEST_MISSING("URLSHORTNER.REQUEST.MISSING", "Request cannot be Null or Empty "),
    URL_MISSING("URL.MISSING.ERROR", "URL is require "),
    INVALID_URL("URL.INVALID.ERROR", "Please provide the correct URL"),
    INVALID_EXPIRY_DATE("INVALID.EXPIRY.DATE", "Please provide the correct expiry date"),
    INVALID_URL_ALIAS("INVALID.URL.ALIAS", "Alias must be greater than 6 and less than 8 characters"),
    INTERNAL_SERVER_ERROR("INVALID.SERVER.ERROR", "Something went wrong. Please contact the support team"),
    ALIAS_ALREADY_EXISTING("URLSHORTNER.ALIAS.NOTAVAILABLE", "Requested ALias is not available"),
    URL_NOT_FOUND("URLSHORTNER.URL.NOTFOUND", "No URL is present for provided key");

    URLErrorCode(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    final private String errorCode;
    final private String errorMessage;
}
