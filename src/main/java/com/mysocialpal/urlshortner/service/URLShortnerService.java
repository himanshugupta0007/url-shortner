/**
 *
 */
package com.mysocialpal.urlshortner.service;

import com.mysocialpal.urlshortner.dto.URLShortnerRequest;
import com.mysocialpal.urlshortner.dto.URLShortnerResponse;

/**
 * Service Class to apply the logic to hash the URL and returns the short url to
 * the client.
 * <p>
 * It also calls the repository to save the short url in the database
 *
 * @author Himanshu.Gupta
 * @version 1.0
 * @since March, 2022
 */
public interface URLShortnerService {

    /**
     * This method generates the Short URL and saves the same in the Database
     *
     * @param urlRequest
     * @return
     */
    URLShortnerResponse generateShortURL(URLShortnerRequest urlRequest);

    /**
     * This method returns the original url for provide URL key
     *
     * @param urlKey String
     * @return String
     */
    String getOriginalURLForRedirect(String urlKey);

    /**
     * This method returns the original url with all relevant information
     *
     * @param urlKey String
     * @return String
     */
    URLShortnerResponse getOriginalURL(String urlKey);
}
