package com.ps.dtx.md.account.service;

import com.ps.dtx.md.account.dao.repository.AccountRepository;
import com.ps.dtx.md.account.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AccountRepository accountRepository;
    @Transactional
    @JmsListener(containerFactory = "jmsListenerContainerFactory",destination = "md:account:queue:debit")
    @Override
    public void debit(Account account) {
        String userId = account.getUserId();
        int money = account.getMoney();
        logger.info("debit account with userId={}, money={}", userId,money);
        Integer affectedRows = this.accountRepository.debitAccount(userId,money);
        logger.info("Account service with affected rows: {}", affectedRows);
    }
}
