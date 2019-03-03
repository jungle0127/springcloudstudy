package com.ps.dtx.fd.client.runner;

import com.ps.dtx.fd.client.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PurchaseRunner implements CommandLineRunner {
    @Autowired
    private BusinessService businessService;
    @Override
    public void run(String... args) throws Exception {
        this.businessService.setFlag(true);
        this.businessService.purchase("uid","cc1",10);
    }
}
