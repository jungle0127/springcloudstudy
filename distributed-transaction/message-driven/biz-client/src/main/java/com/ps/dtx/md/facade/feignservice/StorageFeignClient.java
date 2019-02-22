package com.ps.dtx.md.facade.feignservice;

import com.ps.dtx.md.storage.model.Storage;
import com.ps.dtx.md.storage.service.StorageInfoService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "md-storage", path = "/api/v1/storage")
public interface StorageFeignClient extends StorageInfoService {
    @GetMapping("/{id}")
    Storage getStorage(@PathVariable(name = "id") Integer id);
    @GetMapping("/code/{commodityCode}")
    Storage getStorage(@PathVariable(name = "commodityCode") String commodityCode);
    @PostMapping("/")
    Boolean addStorage(@RequestBody Storage storage);
}
