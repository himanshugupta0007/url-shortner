/**
 * 
 */
package com.mysocialpal.urlshortner.dto;

import com.mysocialpal.urlshortner.common.OutputResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * This class is the response for URL Shortner
 * 
 * @author Himanshu.Gupta
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class URLShortnerResponse extends OutputResponse {

    URLShortnerResponseDTO urlResponse;
}
