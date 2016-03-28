package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * domain object to store application log
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationLog extends Log {

    private String logLevel;

    private String className;

    private String message;

    private String stackTrace;


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public ApplicationLog(String applicationName, String logType, String logLevel, String className, String message, String stackTrace) {
        super(applicationName, logType);
        this.logLevel = logLevel;
        this.className = className;
        this.message = message;
        this.stackTrace = stackTrace;
    }
}
