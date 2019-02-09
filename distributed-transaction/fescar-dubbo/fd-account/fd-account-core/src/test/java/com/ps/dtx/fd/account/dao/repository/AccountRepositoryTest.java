package com.ps.dtx.fd.account.dao.repository;

import com.ps.dtx.fd.account.dao.model.AccountTbl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;
    @Test
    public void addAccount() {
        AccountTbl accountTbl = new AccountTbl();
        accountTbl.setMoney(800);
        accountTbl.setUserId("1");
        Integer affectedRows = this.accountRepository.addAccount(accountTbl);
        Assert.assertTrue(affectedRows == 1);
    }
    @Test
    public void testDebitAccount(){
        Integer affectedRows = this.accountRepository.debitAccount("1",100);
        Assert.assertTrue(affectedRows == 1);
    }
}