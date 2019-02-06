package com.ps.tx.jta.dao.domain;

import com.ps.tx.jta.dao.mapper.TAccountMapper;
import com.ps.tx.jta.dao.model.TAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {
    @Autowired
    private TAccountMapper accountMapper;
    public Integer addAccount(TAccount account){
        return this.accountMapper.insert(account);
    }
}
