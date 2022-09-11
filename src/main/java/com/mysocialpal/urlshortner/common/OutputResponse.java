package com.mysocialpal.urlshortner.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * This includes the list of alerts in the outpur
 * 
 * @author Himanshu.Gupta
 *
 */
@NoArgsConstructor
@AllArgsConstructor
abstract public class OutputResponse {

    List<AlertResponse> alerts = new ArrayList<AlertResponse>();

    /**
     * Retrieve all known alerts that occurred during processing. Alerts are a form of a warning.
     *
     * @return {@link List} of {@link AlertResponse}
     */
    public List<AlertResponse> getAlerts() {
        return alerts;
    }

    /**
     * Builder style convenience method to add a vararg set of {@link AlertResponse} objects.
     */
    public OutputResponse withAlerts(final AlertResponse... alerts) {
        if (null != alerts) {
            this.alerts.addAll(Arrays.asList(alerts));
        }
        return this;
    }
}
