/**
 *
 */
package com.mysocialpal.urlshortner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This is a URL Shortner Response class to shorten the URL
 *
 * @author Himanshu.Gupta
 *
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(includeFieldNames = true)
public class URLShortnerResponseDTO {
    private String originalURL;
    private String generatedUrl;
    private String hash;
    private String alias;
    private boolean isActive;
}
