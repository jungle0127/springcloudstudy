package com.ps.dtx.md.account.controller;

import com.ps.dtx.md.account.dao.repository.AccountRepository;
import com.ps.dtx.md.account.model.Account;
import com.ps.dtx.md.account.service.AccountInfoService;
import com.ps.dtx.md.account.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController implements AccountInfoService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/deposit")
    @Override
    public Integer getAccount(String userId) {
        return this.accountRepository.getAccountDeposit(userId);
    }

}
