package com.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;


/**
 * Properties specific to JHipster.
 *
 * <p>
 *     Properties are configured in the application.yml file.
 * </p>
 */
@ConfigurationProperties(prefix = "loggingApp", ignoreUnknownFields = false)
public class ApplicationProperties {



    private final CorsConfiguration cors = new CorsConfiguration();





}
