/**
 *
 */
package com.mysocialpal.urlshortner.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.mysocialpal.urlshortner.exception.URLErrorCode;
import com.mysocialpal.urlshortner.exception.URLShortnerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mysocialpal.urlshortner.common.ResponseEnvelop;
import com.mysocialpal.urlshortner.dto.URLShortnerRequest;
import com.mysocialpal.urlshortner.dto.URLShortnerResponse;
import com.mysocialpal.urlshortner.service.URLShortnerService;

import java.io.IOException;
import java.util.Objects;

/**
 * URL Shortener REST controller to
 *
 * <ul>
 * <li>Register the URL and returns the short url</li>
 * <li>To redirect the short url received in the request</li>
 * </ul>
 *
 * @author Himanshu.Gupta
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/url/shortner")
@Slf4j
public class URLShortnerController {

    private final URLShortnerService urlService;

    @Autowired
    public URLShortnerController(final URLShortnerService urlService) {
        this.urlService = urlService;
    }

    /**
     * Method to create the Short URL for provided URL
     *
     * @param urlRequest
     * @return
     */
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEnvelop<URLShortnerResponse> createURL(
            @Valid @RequestBody URLShortnerRequest urlRequest) {
        log.info("Received request to shorten the URL");
        return ResponseEnvelop
                .success(urlService.generateShortURL(urlRequest));
    }

    /**
     * Redirects to Original URL for provided Key
     *
     * @param urlKey
     * @return
     */
    @GetMapping(value = "/redirect/{key}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void redirectURL(@PathVariable("key") String urlKey, HttpServletResponse response) throws IOException {
        if (Objects.isNull(urlKey) || urlKey.isEmpty()) {
            throw new URLShortnerException(URLErrorCode.INVALID_URL.getErrorCode(),
                    URLErrorCode.INVALID_URL.getErrorMessage(), HttpStatus.BAD_REQUEST);
        }
        log.info("Received request to get the redirect of request for key: " + urlKey);
        response.sendRedirect(urlService.getOriginalURLForRedirect(urlKey));
    }

    /**
     * Get Original URL for provided Key
     *
     * @param urlKey
     * @return
     */
    @GetMapping(value = "/{key}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEnvelop<URLShortnerResponse> getURL(@PathVariable("key") String urlKey) throws IOException {
        if (Objects.isNull(urlKey) || urlKey.isEmpty()) {
            throw new URLShortnerException(URLErrorCode.INVALID_URL.getErrorCode(),
                    URLErrorCode.INVALID_URL.getErrorMessage(), HttpStatus.BAD_REQUEST);
        }
        log.info("Received request to get the redirect of request for key: " + urlKey);
        return ResponseEnvelop
                .success(urlService.getOriginalURL(urlKey));
    }
}
