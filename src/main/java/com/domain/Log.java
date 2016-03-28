package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This class represents parent class for Log object. Other type of log will extend this class.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Log {


    private String applicationName;

    private String logType;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public Log(String applicationName, String logType) {
        this.applicationName = applicationName;
        this.logType = logType;
    }

    public Log() {
    }
}
