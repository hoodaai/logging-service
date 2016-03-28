package com.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Data transfer object for application log data
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationLogDTO implements LogDTO {

    @NotEmpty
    private String logLevel;

    @NotEmpty
    private String className;

    @NotEmpty
    private String message;

    @NotEmpty
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


}
