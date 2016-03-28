package com.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * DTO for customer activity logs
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerActivityLogDTO implements LogDTO {

    @NotEmpty
    private String productName;

    @NotEmpty
    private String productSkuId;

    @NotEmpty
    private String event;

    @NotEmpty
    private String userId;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSkuId() {
        return productSkuId;
    }

    public void setProductSkuId(String productSkuId) {
        this.productSkuId = productSkuId;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
