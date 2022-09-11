package com.mysocialpal.urlshortner.dto;

import java.time.LocalDate;

import com.mysocialpal.urlshortner.validator.URLRequestValidator;
import lombok.Data;


/**
 * This is a URL Shortner request class to shorten the URL
 *
 * @author Himanshu.Gupta
 */
@URLRequestValidator
@Data
public class URLShortnerRequest {
    private String url;
    private String alias;
}
