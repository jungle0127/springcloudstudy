package com.ps.dtx.fd.account.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fescar.core.context.RootContext;
import com.ps.dtx.fd.account.dao.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Service(timeout = 5000,interfaceClass = AccountService.class)
public class AccountServiceImpl implements AccountService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public void debit(String userId, int money) {
        logger.info("debit account with userId={}, money={}", userId,money);
        logger.info("Global transaction ID: {}", RootContext.getXID());
        Integer affectedRows = this.accountRepository.debitAccount(userId,money);
        logger.info("Account service with affected rows: {}", affectedRows);
    }
}
