package com.ps.dtx.md.facade.controller;

import com.ps.dtx.md.facade.feignservice.StorageFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/facade/storage")
public class StorageController {
    @Autowired
    private StorageFeignClient storageFeignClient;
}
