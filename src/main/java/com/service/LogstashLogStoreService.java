package com.service;

import com.domain.Log;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

/**
 * Logstash implementation for LogStore service
 */
@Component
public class LogstashLogStoreService implements LogStoreService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LogstashLogStoreService.class);

    @Inject
    private Environment env;

    /**
     * This method stores log data to logstash server
     * @param log
     */
    @Override
    public ResponseEntity<?> store(Log log) {
        String url = env.getProperty("logstash.url");
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        ResponseEntity<?> response  = restTemplate.postForEntity(url, log, String.class);
        logger.info(response.toString());
        return response;
    }
}
