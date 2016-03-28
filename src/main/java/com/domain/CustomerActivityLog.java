package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * domain object to store customer activity log
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerActivityLog extends Log {

    private String productName;

    private String productSkuId;

    private String event;

    private String userId;

    public CustomerActivityLog(String applicationName, String logType, String productName,
                               String productSkuId, String event, String userId) {
        super(applicationName, logType);
        this.productName = productName;
        this.productSkuId = productSkuId;
        this.event = event;
        this.userId = userId;
    }

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
