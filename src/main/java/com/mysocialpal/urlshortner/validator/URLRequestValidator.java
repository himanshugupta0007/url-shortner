/**
 * 
 */
package com.mysocialpal.urlshortner.validator;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * This is annotation is used to validate the URL Request Attributes
 * 
 * @author Himanshu.Gupta
 *
 */
@Retention(RUNTIME)
@Constraint(validatedBy = URLRequestValidationImpl.class)
@Target({METHOD, TYPE, ANNOTATION_TYPE})
public @interface URLRequestValidator {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
