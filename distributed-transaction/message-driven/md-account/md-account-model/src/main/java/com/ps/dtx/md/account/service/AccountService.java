package com.ps.dtx.md.account.service;

import com.ps.dtx.md.account.model.Account;

public interface AccountService {
    /**
     * debit balance of user's account
     */
    void debit(Account account);
}
