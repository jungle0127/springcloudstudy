package com.ps.infrastructure.database.multiple.dynamic.datasource.dao.storage.repository;

import com.ps.infrastructure.database.multiple.dynamic.datasource.dao.storage.mapper.TStorageMapper;
import com.ps.infrastructure.database.multiple.dynamic.datasource.dao.storage.model.TStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StorageRepository {
    @Autowired
    private TStorageMapper storageMapper;
    public Integer addStorage(TStorage storage){
        return this.storageMapper.insert(storage);
    }
    public List<TStorage> getAll(){
        return this.storageMapper.selectAll();
    }
}
