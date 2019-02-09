package com.ps.dtx.fd.storage.dao.repository;


import com.ps.dtx.fd.storage.dao.model.StorageTbl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StorageRepositoryTest {
    @Autowired
    private StorageRepository storageRepository;
    @Test
    public void addStorage() {
        StorageTbl storageTbl = new StorageTbl();
        storageTbl.setCommodityCode("666");
        storageTbl.setCount(1000);
        Integer affectedRows = this.storageRepository.addStorage(storageTbl);
        Assert.assertTrue(affectedRows == 1);
    }
}