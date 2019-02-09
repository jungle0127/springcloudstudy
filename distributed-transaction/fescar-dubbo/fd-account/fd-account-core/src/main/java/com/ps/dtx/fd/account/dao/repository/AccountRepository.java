package com.ps.dtx.fd.account.dao.repository;

import com.ps.dtx.fd.account.dao.mapper.AccountTblMapper;
import com.ps.dtx.fd.account.dao.model.AccountTbl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {
    @Autowired
    private AccountTblMapper accountTblMapper;

    public Integer addAccount(AccountTbl accountTbl){
        return this.accountTblMapper.insert(accountTbl);
    }
}
