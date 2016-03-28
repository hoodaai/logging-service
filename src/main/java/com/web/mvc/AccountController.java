package com.web.mvc;

import com.domain.Account;
import com.service.AccountService;
import com.web.mvc.dto.AccountRequest;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * MVC controller for account registration.
 */

@Controller
@RequestMapping("/account")
public class AccountController extends WebMvcConfigurerAdapter{
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AccountController.class);


    @Inject
    private AccountService accountService;

    /**
     * This method receives account registration request from web.
     * @param accountRequest
     * @param bindingResult
     * @param model
     * @return return a model which contains appId and appSecret
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST,
            headers = "content-type=application/x-www-form-urlencoded")
    public String registerAccount(@Valid AccountRequest accountRequest,  BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getFieldError().getDefaultMessage());
            return "home";
        }

        Account account = accountService.createAccount(accountRequest);
        model.addAttribute("appId", account.getAppId());
        model.addAttribute("appSecret", account.getAppSecret());

        return "account";
    }
}
