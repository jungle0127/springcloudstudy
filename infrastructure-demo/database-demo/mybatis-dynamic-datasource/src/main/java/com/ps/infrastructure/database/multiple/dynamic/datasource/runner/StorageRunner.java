package com.ps.infrastructure.database.multiple.dynamic.datasource.runner;

import com.ps.infrastructure.database.multiple.dynamic.datasource.dao.storage.mapper.TStorageMapper;
import com.ps.infrastructure.database.multiple.dynamic.datasource.dao.storage.model.TStorage;
import com.ps.infrastructure.database.multiple.dynamic.datasource.dao.storage.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class StorageRunner implements CommandLineRunner {
    @Autowired
    private StorageRepository storageRepository;
    @Override
    public void run(String... args) throws Exception {
        TStorage storage = new TStorage();
        storage.setCreateTime(new Date());
        storage.setInventory(200);
        storage.setProductName("double source product.");
        storage.setUpdateTime(new Date());
        Integer affectedRows = this.storageRepository.addStorage(storage);
        System.out.println("Add storage affected rows:" + affectedRows);
        List<TStorage> storageList = this.storageRepository.getAll();
        for(TStorage pojo: storageList){
            System.out.println(pojo.toString());
        }
    }
}
