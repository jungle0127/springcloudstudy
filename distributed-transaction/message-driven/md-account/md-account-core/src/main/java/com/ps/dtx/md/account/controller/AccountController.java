package com.ps.dtx.md.account.controller;

import com.ps.dtx.md.account.dao.repository.AccountRepository;
import com.ps.dtx.md.account.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController implements AccountService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AccountRepository accountRepository;
    @PostMapping("/debit")
    @Override
    public void debit(String userId, int money) {
        logger.info("debit account with userId={}, money={}", userId,money);
        Integer affectedRows = this.accountRepository.debitAccount(userId,money);
        logger.info("Account service with affected rows: {}", affectedRows);
    }
}
