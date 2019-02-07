package com.ps.dtx.basictransaction.doubledb.jta.dao.storage.repository;

import com.ps.dtx.basictransaction.doubledb.jta.dao.storage.mapper.TStorageMapper;
import com.ps.dtx.basictransaction.doubledb.jta.dao.storage.model.TStorage;
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
