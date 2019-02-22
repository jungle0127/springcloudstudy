package com.ps.dtx.md.facade.controller;

import com.ps.dtx.md.facade.feignservice.AccountFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/facade/account")
public class AccountController {
    @Autowired
    private AccountFeignClient accountFeignClient;

    @GetMapping("/deposit/{userId}")
    public Integer getAccount(@PathVariable String userId){
        return this.accountFeignClient.getAccount(userId);
    }
}
