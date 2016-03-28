package com.service;

import com.Application;
import com.domain.Account;
import com.web.mvc.dto.AccountRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;

/**
 * Test cases for AccountServcie
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    private final String APP_NAME = "testapp";
    private final String APP_NAME_TWO = "email_app";


    /**
     * Test application account registration.
     * @throws IOException
     * expected: account should be created with valid appId and appSecret
     *
     */
    @Test
    public void testAccountRegistration() throws IOException {
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setAppName(APP_NAME);
        Account account = accountService.createAccount(accountRequest);
        Assert.assertEquals(account.getAppName(), APP_NAME);
        Assert.assertNotNull(account.getAppSecret());
    }


    /**
     * Test verifyAppSecret method in AccountService.
     * @throws IOException
     * expected: appName must match for registered account and verified account.
     *
     */
    @Test
    public void testVerifyAppSecret() throws IOException {
        //Register an account
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setAppName(APP_NAME_TWO);
        Account registeredAccount = accountService.createAccount(accountRequest);

        //verify appsecret
        Account  verifiedAccount =  accountService.verifyAppSecret(registeredAccount.getAppSecret());
        Assert.assertEquals(verifiedAccount.getAppName(), registeredAccount.getAppName());
    }
}
