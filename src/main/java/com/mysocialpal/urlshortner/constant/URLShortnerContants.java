/**
 *
 */
package com.mysocialpal.urlshortner.constant;

/**
 * Enum to maintain constants used in the application
 *
 * @author Himanshu.Gupta
 * @version 1.0
 * @since March, 2022
 */
public enum URLShortnerContants {

    GENERATE_KEY_ALGO("SHA1PRNG"),
    GENERATE_KEY_PROVIDER("SUN"),
    LOCAL_USER("LOCAL_USER"), SHA_256("SHA-256");

    private String constant;

    URLShortnerContants(String constant) {
        this.constant = constant;
    }

    public String getConstant() {
        return constant;
    }
}
