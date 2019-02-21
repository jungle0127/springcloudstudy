package com.ps.dtx.md.facade.feignservice;

import com.ps.dtx.md.account.service.AccountInfoService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "md-account",path = "/api/v1/account")
public interface AccountFeignClient extends AccountInfoService {
    @GetMapping("/deposit/{userId}")
    Integer getAccount(@PathVariable String userId);
}
