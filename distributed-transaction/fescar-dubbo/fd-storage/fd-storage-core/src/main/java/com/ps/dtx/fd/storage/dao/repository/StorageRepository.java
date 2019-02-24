package com.ps.dtx.fd.storage.dao.repository;

import com.ps.dtx.fd.storage.dao.mapper.StorageTblMapper;
import com.ps.dtx.fd.storage.dao.model.StorageTbl;
import com.ps.dtx.fd.storage.model.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StorageRepository {
    @Autowired
    private StorageTblMapper storageTblMapper;

    public Integer addStorage(StorageTbl storageTbl){
        return this.storageTblMapper.insert(storageTbl);
    }
    public Integer updateInventory(String commodityCode, Integer count){
        return this.storageTblMapper.updateInventory(commodityCode,count);
    }
    public Storage getStorage(Integer id){
        StorageTbl storageTbl = this.storageTblMapper.selectByPrimaryKey(id);
        return transform(storageTbl);
    }
    public Storage getStorage(String commodityCode){
        StorageTbl storageTbl = this.storageTblMapper.selectByCommodityCode(commodityCode);
        return transform(storageTbl);
    }
    private Storage transform(StorageTbl storageTbl) {
        if(storageTbl == null){
            return new Storage();
        }
        Storage storage = new Storage();
        storage.setCommodityCode(storageTbl.getCommodityCode());
        storage.setCount(storageTbl.getCount());
        storage.setId(storageTbl.getId());
        return storage;
    }
}
