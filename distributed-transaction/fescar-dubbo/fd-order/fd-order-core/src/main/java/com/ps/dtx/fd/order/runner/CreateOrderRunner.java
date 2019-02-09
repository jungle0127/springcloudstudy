package com.ps.dtx.fd.order.runner;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ps.dtx.fd.account.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class CreateOrderRunner implements CommandLineRunner {
    @Reference(interfaceClass = AccountService.class)
    private AccountService accountService;
    @Override
    public void run(String... args) throws Exception {
        this.accountService.debit("1",100);
    }
}
