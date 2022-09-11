/**
 * 
 */
package com.mysocialpal.urlshortner.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Wrapper class for response formmatted as below
 * 
 * <code>
 *  * {
 *   "transactionId": "string",
 *   "output": {
 *     "alerts": [
 *       {
 *         "code": "string",
 *         "message": "string",
 *         "alertType": "WARNING"
 *       }
 *     ],
 *     "yourCustomResponseContent": {
 *       // YOUR CUSTOM CONTENT GOES HERE!
 *     }
 *   },
 *   "errors": [
 *     {
 *       "code": "string",
 *       "message": "string"
 *     }
 *   ]
 * }
 * </code>
 * 
 * 
 * 
 * @author Himanshu.Gupta
 *
 */
@SuppressWarnings("rawtypes")
public class ResponseEnvelop<T extends OutputResponse> {

    private String transactionId;

    private List<ErrorResponse> errors = new ArrayList<>();

    private T output;

    /**
     * Default constructor used for jackson (de)serialization and when no properties exist (they're all optional)
     */
    private ResponseEnvelop() {}

    /**
     * Default constructor for a successful envelope, but no relevant output
     */
    private ResponseEnvelop(final String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * Create a ResponseEnvelop that contains output and a transactionId
     */
    private ResponseEnvelop(final String transactionId, final T output) {
        this.transactionId = transactionId;
        this.output = output;
    }

    /**
     * Create a ResponseEnvelop that does not contain a transactionId
     */
    private ResponseEnvelop(final T output) {
        this(null, output);
    }

    /**
     * Create a ResponseEnvelop that contains errors
     */
    private ResponseEnvelop(final String transactionId, final List<ErrorResponse> errors) {
        this.transactionId = transactionId;
        this.errors = errors;
    }

    /**
     * Create a ResponseEnvelop that contains errors
     */
    private ResponseEnvelop(final String transactionId, final ErrorResponse... errors) {
        this(transactionId, Arrays.asList(errors));

    }

    /**
     * Create a CXS Envelope that contains a single error
     */
    private ResponseEnvelop(final String transactionId, final String errorCode, final String errorMessage) {
        this(transactionId, new ErrorResponse(errorCode, errorMessage));
    }

    /**
     * Create a CXS Envelope that has no transaction id or outputs
     */

    public static ResponseEnvelop success() {
        return new ResponseEnvelop();
    }

    /**
     * Create a CXS Envelope that is successful, but has no relevant outputs.
     */
    public static ResponseEnvelop success(final String transactionId) {
        return new ResponseEnvelop(transactionId);
    }

    /**
     * Create a ResponseEnvelop that contains output and a transactionId
     */
    public static <R extends OutputResponse> ResponseEnvelop<R> success(final String transactionId, final R output) {
        return new ResponseEnvelop<>(output);
    }

    /**
     * Create a ResponseEnvelop that does not contain a transactionId
     */
    public static <R extends OutputResponse> ResponseEnvelop<R> success(final R output) {
        return new ResponseEnvelop<>(output);
    }

    /**
     * Build a ResponseEnvelop that contains errors
     */
    public static ResponseEnvelop error(final String transactionId, final String errorCode, final String errorMessage) {
        return new ResponseEnvelop<>(transactionId, errorCode, errorMessage);
    }

    /**
     * Build a ResponseEnvelop that contains errors
     */
    public static ResponseEnvelop error(final String errorCode, final String errorMessage) {
        return new ResponseEnvelop<>(null, errorCode, errorMessage);
    }

    /**
     * Build a ResponseEnvelop that contains errors
     */
    public static ResponseEnvelop error(final String transactionId, final List<ErrorResponse> errors) {
        return new ResponseEnvelop<>(transactionId, errors);
    }

    /**
     * Build a ResponseEnvelop that contains errors
     */
    public static ResponseEnvelop error(final List<ErrorResponse> errors) {
        return new ResponseEnvelop<>(null, errors);
    }

    /**
     * Build a ResponseEnvelop that contains errors
     */
    public static ResponseEnvelop error(final String transactionId, final ErrorResponse... errors) {
        return new ResponseEnvelop<>(transactionId, errors);
    }

    /**
     * Build a ResponseEnvelop that contains errors
     */
    public static ResponseEnvelop error(final ErrorResponse... errors) {
        return new ResponseEnvelop<>(null, errors);
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public List<ErrorResponse> getErrors() {
        return errors;
    }

    public T getOutput() {
        return output;
    }

    @Override
    public String toString() {
        return "ResponseEnvelop{" + "transactionId='" + transactionId + '\'' + ", errors=" + errors + ", output="
                        + output + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResponseEnvelop)) {
            return false;
        }
        ResponseEnvelop<?> that = (ResponseEnvelop<?>) o;
        return Objects.equals(transactionId, that.transactionId) && Objects.equals(errors, that.errors)
                        && Objects.equals(output, that.output);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, errors, output);
    }

}
