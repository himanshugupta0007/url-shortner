/**
 * 
 */
package com.mysocialpal.urlshortner.common;

import java.util.Objects;

/**
 * This is common Alert Response Class to add warnings or alerts for provided response
 * 
 * @author Himanshu.Gupta
 *
 */
public class AlertResponse {
    private String code;
    private String message;
    private String alertType;

    public AlertResponse() {
        super();
    }

    /**
     * @param code
     * @param message
     * @param alertType
     */
    public AlertResponse(String code, String message, String alertType) {
        this.code = code;
        this.message = message;
        this.alertType = alertType;
    }

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

    /**
     * @return the alertType
     */
    public String getAlertType() {
        return alertType;
    }

    /**
     * @param alertType the alertType to set
     */
    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    @Override
    public String toString() {
        return "AlertResponse [code=" + code + ", message=" + message + ", alertType=" + alertType + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(alertType, code, message);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AlertResponse other = (AlertResponse) obj;
        return Objects.equals(alertType, other.alertType) && Objects.equals(code, other.code)
                        && Objects.equals(message, other.message);
    }
}
