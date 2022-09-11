package com.mysocialpal.urlshortner.utility;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

/**
 * This interface generates the Unique key that will be used to concatenate with URL to generate unique Hash and avoid
 * collision
 * 
 * @author Himanshu.Gupta
 * @version 1.0
 * @since March, 2022
 *
 */
public interface URLShortnerUtility {

    /**
     * This methods uses the {@link SecureRandom} library.
     * 
     * This unique salt will be concatenated with the URL to generate the unique hash for provided URL
     * 
     * @return String Unique Key
     * 
     * @throws NoSuchProviderException, NoSuchAlgorithmException
     * 
     */
    public String generateUniqueKey() throws NoSuchAlgorithmException, NoSuchProviderException;
}

