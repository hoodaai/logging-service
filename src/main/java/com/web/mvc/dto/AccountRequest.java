package com.web.mvc.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Account DTO. used to bind account registration request parameters into a valid request.
 */
public class AccountRequest {

    @NotNull(message = "Application name is compulsory")
    @NotBlank(message = "Application name is compulsory")
    @Pattern(regexp = "[a-z-A-Z]*", message = "Application name has invalid characters")
    private String appName;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
