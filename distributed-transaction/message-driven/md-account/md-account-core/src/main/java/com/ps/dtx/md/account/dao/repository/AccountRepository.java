package com.ps.dtx.md.account.dao.repository;

import com.ps.dtx.md.account.dao.mapper.AccountTblMapper;
import com.ps.dtx.md.account.dao.model.AccountTbl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {
    @Autowired
    private AccountTblMapper accountTblMapper;

    public Integer addAccount(AccountTbl accountTbl){
        return this.accountTblMapper.insert(accountTbl);
    }

    public Integer debitAccount(String userId,Integer money){
        return this.accountTblMapper.updateAccount(userId,money);
    }
    public Integer getAccountDeposit(String userId){
        AccountTbl accountTbl = this.accountTblMapper.selectMoneyByUserId(userId);
        return accountTbl == null ? null : accountTbl.getMoney();
    }
}
