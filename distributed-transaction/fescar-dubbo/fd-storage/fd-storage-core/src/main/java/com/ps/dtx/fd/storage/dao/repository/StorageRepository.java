package com.ps.dtx.fd.storage.dao.repository;

import com.ps.dtx.fd.storage.dao.mapper.StorageTblMapper;
import com.ps.dtx.fd.storage.dao.model.StorageTbl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StorageRepository {
    @Autowired
    private StorageTblMapper storageTblMapper;

    public Integer addStorage(StorageTbl storageTbl){
        return this.storageTblMapper.insert(storageTbl);
    }
}
