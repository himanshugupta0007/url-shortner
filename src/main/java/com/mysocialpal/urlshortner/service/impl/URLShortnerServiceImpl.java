package com.mysocialpal.urlshortner.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Objects;

import com.mysocialpal.urlshortner.exception.URLErrorCode;
import com.mysocialpal.urlshortner.exception.URLShortnerException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mysocialpal.urlshortner.config.TrackTime;
import com.mysocialpal.urlshortner.constant.URLShortnerContants;
import com.mysocialpal.urlshortner.dto.URLShortnerRequest;
import com.mysocialpal.urlshortner.dto.URLShortnerResponse;
import com.mysocialpal.urlshortner.dto.URLShortnerResponseDTO;
import com.mysocialpal.urlshortner.entity.URLShortner;
import com.mysocialpal.urlshortner.repository.URLRepository;
import com.mysocialpal.urlshortner.service.URLShortnerService;
import com.mysocialpal.urlshortner.utility.URLShortnerUtility;

/**
 * Service Class Implementation to apply the logic to hash the URL and returns
 * the short url to the client.
 * <p>
 * It also calls the repository to save the short url in the database
 *
 * @author Himanshu.Gupta
 * @version 1.0
 * @since March, 2022
 */
@Service
@Slf4j
public class URLShortnerServiceImpl implements URLShortnerService {

    private final URLShortnerUtility urlUtility;

    private final URLRepository urlRepository;

    @Value("${url.domain}")
    private String urlDomaim;

    @Autowired
    public URLShortnerServiceImpl(final URLShortnerUtility urlUtility, final URLRepository urlRepository) {
        this.urlUtility = urlUtility;
        this.urlRepository = urlRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TrackTime
    public URLShortnerResponse generateShortURL(URLShortnerRequest urlRequest) {
        URLShortnerResponseDTO urlResponseDTO = null;
        URLShortner existingURL = null;
        try {

            if (Objects.nonNull(urlRequest.getAlias()))
                existingURL = urlRepository.findURLByAlias(urlRequest.getAlias());
            // if existing URL is avaialble for alias, send error that alias is used
            if (Objects.nonNull(existingURL)) {
                log.error("Alias already existing: " + urlRequest.getUrl());
                throw new URLShortnerException(URLErrorCode.ALIAS_ALREADY_EXISTING.getErrorCode(),
                        URLErrorCode.ALIAS_ALREADY_EXISTING.getErrorMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
            } else {
                log.info("Generating URL Shortner for provided URL: " + urlRequest.getUrl());
                String hashingSalt = urlUtility.generateUniqueKey();
                MessageDigest digest = MessageDigest.getInstance(URLShortnerContants.SHA_256.getConstant());
                byte[] hash = digest.digest((urlRequest.getUrl() + ":" + hashingSalt).getBytes(StandardCharsets.UTF_8));
                String encodedURL =
                        Base64.getEncoder().withoutPadding().encodeToString(hash)
                                .replace("/", "").substring(5, 13);
                String alias = (urlRequest.getAlias() == null || urlRequest.getAlias().isEmpty()) ? encodedURL :
                        urlRequest.getAlias();
                URLShortner urlShortned =
                        urlRepository.saveAndFlush(populateTheURLEntitity(urlRequest, hashingSalt, alias, encodedURL));
                String urlGenerated = urlDomaim.concat((alias == null || alias.isEmpty()) ? encodedURL : alias);
                urlResponseDTO =
                        new URLShortnerResponseDTO(null, urlGenerated, urlShortned.getUrl_hash(),
                                alias, urlShortned.is_active());
            }
        } catch (DataException | NoSuchAlgorithmException | NoSuchProviderException e) {
            log.error("Exception occurred:", e);
            throw new URLShortnerException(URLErrorCode.INTERNAL_SERVER_ERROR.getErrorCode(),
                    URLErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new URLShortnerResponse(urlResponseDTO);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getOriginalURLForRedirect(String urlKey) {
        URLShortnerResponse urlShortnerResponse = getOriginalURL(urlKey);
        return urlShortnerResponse.getUrlResponse().getOriginalURL();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public URLShortnerResponse getOriginalURL(String urlKey) {
        URLShortnerResponseDTO urlResponseDTO;
        try {
            log.info("Retrieve URL for provide key: " + urlKey);
            URLShortner urlShortner = urlRepository.findURLByAlias(urlKey);
            if (Objects.isNull(urlShortner))
                throw new URLShortnerException(URLErrorCode.URL_NOT_FOUND.getErrorCode(),
                        URLErrorCode.URL_NOT_FOUND.getErrorMessage(), HttpStatus.NOT_FOUND);
            String urlGenerated = urlDomaim.concat(urlShortner.getUrl_alias());
            urlResponseDTO =
                    new URLShortnerResponseDTO(urlShortner.getOrg_url(), urlGenerated, urlShortner.getUrl_hash(), urlShortner.getUrl_alias(),
                            urlShortner.is_active());
        } catch (DataException ex) {
            log.error("Exception occurred:", ex);
            throw new URLShortnerException(URLErrorCode.INTERNAL_SERVER_ERROR.getErrorCode(),
                    URLErrorCode.INTERNAL_SERVER_ERROR.getErrorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new URLShortnerResponse(urlResponseDTO);
    }

    /**
     * This method populates the {@link URLShortner} entity using {@link URLShortnerRequest}
     *
     * @param urlRequest  {@link URLShortnerRequest}
     * @param hashingSalt Hashing Salt
     * @param alias       Alias for the URL
     * @param encodedURL
     * @return URLShortner
     */
    private URLShortner populateTheURLEntitity(URLShortnerRequest urlRequest, String hashingSalt, String alias,
                                               String encodedURL) {
        URLShortner urlShortner = new URLShortner();
        urlShortner.setOrg_url(urlRequest.getUrl());
        urlShortner.setUrl_hash(encodedURL);
        urlShortner.setUrl_alias(alias);
        urlShortner.setExpires_on(LocalDate.now().plusYears(1));
        urlShortner.set_active(true);
        urlShortner.setUrl_slt(hashingSalt);
        return urlShortner;
    }

}
