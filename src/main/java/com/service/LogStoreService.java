package com.service;

import com.domain.Log;
import org.springframework.http.ResponseEntity;

/**
 * Interface for log Store
 */
public interface LogStoreService {

    /**
     * This method stores the log data to the implemented receiver.
     * @param log
     */
    ResponseEntity<?> store(Log log);
}
