package com.ps.dtx.fd.account.service;

public interface AccountService {
    /**
     * debit balance of user's account
     */
    void debit(String userId, int money);
}
