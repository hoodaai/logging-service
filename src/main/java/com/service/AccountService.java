package com.service;

import com.domain.Account;
import com.repositories.AccountRepository;
import com.service.util.RandomUtil;
import com.web.mvc.dto.AccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Service layer for registration account creation
 */

@Service
@Transactional
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * register an application for logging service.
     * @param accountRequest
     * @return Account
     */
    public Account createAccount(AccountRequest accountRequest) {

        Account account = new Account.Builder(accountRequest.getAppName(),
                                             RandomUtil.generateApplicationId(),
                                             RandomUtil.generateSecretKey()).activated(true).build();

        return accountRepository.save(account);
    }

    /**
     * This method verifies the appSecret and return Account
     * @param appSecret
     * @return Account
     */
    public Account verifyAppSecret(String appSecret) {

        return accountRepository.findByAppSecret(appSecret);
    }
}
