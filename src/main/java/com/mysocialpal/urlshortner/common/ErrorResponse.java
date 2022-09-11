/**
 * 
 */
package com.mysocialpal.urlshortner.common;

import java.util.Objects;

/**
 * This class includes the error messages and code
 * 
 * @author Himanshu.Gupta
 *
 */
public class ErrorResponse {

    private String code;
    private String message;

    /**
     * @param code
     * @param message
     */
    public ErrorResponse(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    /**
     * 
     */
    public ErrorResponse() {}

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ErrorResponse other = (ErrorResponse) obj;
        return Objects.equals(code, other.code) && Objects.equals(message, other.message);
    }

    @Override
    public String toString() {
        return "ErrorResponse [code=" + code + ", message=" + message + "]";
    }

}
