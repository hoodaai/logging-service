package com.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * domain object to store application account
 */

@Entity
@Table(name = "account", uniqueConstraints = @UniqueConstraint(columnNames = {"app_name"}))
public class Account implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "app_name", length = 50, unique = true, nullable = false)
    private String appName;


    @Column(nullable = false)
    private boolean activated = false;

    @Size(max = 50)
    @Column(name = "app_id", length = 50)
    private String appId;

    @Size(max = 50)
    @Column(name = "app_secret", length = 50)
    private String appSecret;


    @Size(max = 50)
    @Column(name = "password_hash", length = 50)
    private String passwordHash;


    /**
     * Builder pattern to create Account object
     */
    public static class Builder {
        // Required parameters
        private final String appName;
        private final String appSecret;
        private final String appId;


        // Optional parameters
        private  String passwordHash;
        private  boolean activated;

        public Builder(String appName, String appId, String appSecret) {
            this.appName = appName;
            this.appId    = appId;
            this.appSecret    = appSecret;
        }

        public Builder activated(boolean val) {
            activated = val;
            return this;
        }

        public Builder passwordHash(String val)
        {
            passwordHash = val;
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }

    private Account(Builder builder) {
        appName  = builder.appName;
        appSecret     = builder.appSecret;
        appId     = builder.appId;
        activated     = builder.activated;
        passwordHash          = builder.passwordHash;
    }

    public Account() {
    }


    public String getAppName() {
        return appName;
    }

    public boolean isActivated() {
        return activated;
    }

    public String getAppId() {
        return appId;
    }

    public String getAppSecret() {
        return appSecret;
    }
}
