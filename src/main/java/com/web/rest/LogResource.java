package com.web.rest;

import com.config.Constants;
import com.config.LogType;
import com.exception.ApplicationAuthorizationException;
import com.service.LogServiceFacade;
import com.web.rest.dto.ApplicationLogDTO;
import com.web.rest.dto.CustomerActivityLogDTO;
import com.web.rest.dto.ResponseDTO;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * REST API for logging component. It provide apis to store log data into logstash server
 */
@RestController
@RequestMapping("/api/log")
public class LogResource {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LogResource.class);


    @Inject
    private LogServiceFacade logServiceFacade;

    /**
     * Stores application log data
     * @param log
     * @param authHeader
     * @return ResponseEntity
     * @throws ApplicationAuthorizationException
     *
     */
    @RequestMapping(value = "/application", method = RequestMethod.POST)
    public ResponseEntity<ResponseDTO> storeApplicationLog(@Valid @RequestBody ApplicationLogDTO log,
                                                           @RequestHeader(value="Authorization") String authHeader)
            throws ApplicationAuthorizationException{

        ResponseEntity<?> response = logServiceFacade.processLog(log, authHeader, LogType.APPLICATION);
        return new ResponseEntity<>(new ResponseDTO(response.getStatusCode().toString(), Constants.OPERATION_SUCCESS),
                                    HttpStatus.CREATED);
    }

    /**
     * Stores customer activity log data
     * @param log
     * @param authHeader
     * @return ResponseEntity
     * @throws ApplicationAuthorizationException
     */
    @RequestMapping(value = "/customerActivity", method = RequestMethod.POST)
    public ResponseEntity<ResponseDTO> storeCustomerActivityLog(@Valid @RequestBody CustomerActivityLogDTO log,
                                                                @RequestHeader(value="Authorization") String authHeader)
            throws ApplicationAuthorizationException{

        logger.info("log data received" + log);
        ResponseEntity<?> response =logServiceFacade.processLog(log, authHeader, LogType.CUSTOMER_ACTIVITY);
        return new ResponseEntity<>(new ResponseDTO(response.getStatusCode().toString(), Constants.OPERATION_SUCCESS),
                                    HttpStatus.CREATED);
    }


}
