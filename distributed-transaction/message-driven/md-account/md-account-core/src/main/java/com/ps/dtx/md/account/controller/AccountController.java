package com.ps.dtx.md.account.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ps.dtx.md.account.dao.repository.AccountRepository;
import com.ps.dtx.md.account.service.AccountInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController implements AccountInfoService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/deposit/{userId}")
    @HystrixCommand
    @Override
    public Integer getAccount(@PathVariable String userId) {
        return this.accountRepository.getAccountDeposit(userId);
    }

}
