package com.ps.tx.jta.runner;

import com.ps.tx.jta.dao.model.TAccount;
import com.ps.tx.jta.service.AccountServiceInAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AnnotationRunner implements CommandLineRunner {
    @Autowired
    private AccountServiceInAnnotation accountServiceInAnnotation;
    @Override
    public void run(String... args) throws Exception {
        TAccount account = new TAccount();
        account.setCustomerId(1L);
        account.setDeposit(100);
        account.setCreateTime(new Date());
        account.setUpdateTime(new Date());
        account = this.accountServiceInAnnotation.createAccount(account);
        System.out.println(account.toString());
    }
}
