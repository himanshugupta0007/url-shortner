/**
 * 
 */
package com.mysocialpal.urlshortner.utility;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import com.mysocialpal.urlshortner.config.TrackTime;
import com.mysocialpal.urlshortner.constant.URLShortnerContants;

/**
 * This interface generates the Unique key that will be used to concatenate with
 * URL to generate unique Hash and avoid collision
 * 
 * @author Himanshu.Gupta
 * @version 1.0
 * @since March, 2022
 *
 */
@Component
public class URLShortnerUtilityImpl implements URLShortnerUtility {

	/**
	 * {@inheritDoc}
	 */
	@TrackTime
	@Override
	public synchronized String generateUniqueKey() throws NoSuchAlgorithmException, NoSuchProviderException {
		final SecureRandom r = SecureRandom.getInstance(URLShortnerContants.GENERATE_KEY_ALGO.getConstant(),
				URLShortnerContants.GENERATE_KEY_PROVIDER.getConstant());
		// generate 16 bit of salt
		byte[] salt = new byte[16];
		r.nextBytes(salt);
		return Base64.encodeBase64URLSafeString(salt);
	}

}
