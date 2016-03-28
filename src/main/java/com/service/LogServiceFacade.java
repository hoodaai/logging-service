package com.service;

import com.config.LogType;
import com.domain.Account;
import com.domain.ApplicationLog;
import com.domain.CustomerActivityLog;
import com.domain.Log;
import com.exception.ApplicationAuthorizationException;
import com.web.rest.dto.ApplicationLogDTO;
import com.web.rest.dto.CustomerActivityLogDTO;
import com.web.rest.dto.LogDTO;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Facade for log service. It acts a single point for LogResource REST service.
 */
@Component
public class LogServiceFacade {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LogServiceFacade.class);

    @Autowired
    private LogStoreService logStoreService;

    @Inject
    private AccountService accountService;

    /**
     * This method process log data. It performs following actions
     * <ul>
     *     <li>Verifies app secret</li>
     *     <li>Creates Log object on the basis of log type</li>
     *     <li>Calls logstore service to send data to logstash server/li>
     * </ul>
     * @param logDTO
     * @param authHeader
     * @param logType
     * @throws ApplicationAuthorizationException
     */
    public ResponseEntity<?> processLog(LogDTO logDTO, String authHeader, LogType logType) throws ApplicationAuthorizationException {
        Account account = accountService.verifyAppSecret(extractToken(authHeader));
        if(account == null){
            throw new ApplicationAuthorizationException("Application is not authorized");
        }
        Log log = null;
        if (logType.equals(LogType.APPLICATION)){
            ApplicationLogDTO applicationLogDTO = (ApplicationLogDTO)logDTO;
             log = new ApplicationLog(account.getAppName(), logType.toString(), applicationLogDTO.getLogLevel(),
                    applicationLogDTO.getClassName(),applicationLogDTO.getMessage(),applicationLogDTO.getStackTrace());
        } else if (logType.equals(LogType.CUSTOMER_ACTIVITY)){
            CustomerActivityLogDTO customerActivityLogDTO = (CustomerActivityLogDTO)logDTO;
            log = new CustomerActivityLog(account.getAppName(), logType.toString(), customerActivityLogDTO.getProductName(),
                    customerActivityLogDTO.getProductSkuId(), customerActivityLogDTO.getEvent(), customerActivityLogDTO.getUserId());
        }

        return logStoreService.store(log);
    }

    /**
     * helper method to extract app secret from authorization header.
     * Expected authorization header is: Bearer QimMaKNSUk
     * @param authHeader
     * @return String appSecret
     */
    private String extractToken(String authHeader){
        return authHeader.split(" ")[1];
    }
}
