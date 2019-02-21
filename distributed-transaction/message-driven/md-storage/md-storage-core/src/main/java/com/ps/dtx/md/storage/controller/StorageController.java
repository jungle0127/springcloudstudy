package com.ps.dtx.md.storage.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ps.dtx.md.storage.dao.model.StorageTbl;
import com.ps.dtx.md.storage.dao.repository.StorageRepository;
import com.ps.dtx.md.storage.model.Storage;
import com.ps.dtx.md.storage.service.StorageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/storage")
public class StorageController implements StorageInfoService {
    @Autowired
    private StorageRepository storageRepository;
    @Override
    @GetMapping("/{id}")
    @HystrixCommand
    public Storage getStorage(@PathVariable Integer id){
        return this.storageRepository.getStorage(id);
    }
    @Override
    @GetMapping("/code/{commodityCode}")
    @HystrixCommand
    public Storage getStorage(@PathVariable String commodityCode){
        return this.storageRepository.getStorage(commodityCode);
    }
    @Override
    @PostMapping("/")
    @HystrixCommand
    public Boolean addStorage(@RequestBody Storage storage){
        StorageTbl storageTbl = transform(storage);
        Integer affectedRows = this.storageRepository.addStorage(storageTbl);
        return affectedRows == 1;
    }

    private StorageTbl transform(Storage storage) {
        if(storage == null){
            return new StorageTbl();
        }
        StorageTbl tbl = new StorageTbl();
        tbl.setCommodityCode(storage.getCommodityCode());
        tbl.setCount(storage.getCount());
        return tbl;
    }
}
