/**
 * 
 */
package com.mysocialpal.urlshortner.config;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * This annotation is created to get Tack Time annotation to track time on
 * method using AOP
 * 
 * @author Himanshu.Gupta
 *
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface TrackTime {

}
