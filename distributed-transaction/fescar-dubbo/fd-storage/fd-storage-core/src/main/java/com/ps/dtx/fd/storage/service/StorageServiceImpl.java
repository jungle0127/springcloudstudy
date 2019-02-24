package com.ps.dtx.fd.storage.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.ps.dtx.fd.storage.dao.repository.StorageRepository;
import com.ps.dtx.fd.storage.model.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class StorageServiceImpl implements StorageService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private StorageRepository storageRepository;
    @Override
    public void deduct(String commodityCode, int count) {
        logger.info("deduct inventory with commodity code={}, and count={}", commodityCode,count);
        Integer affectedRows = this.storageRepository.updateInventory(commodityCode,count);
        if(affectedRows == 1){
            logger.info("deduct inventory operation succeed.");
        }
        else{
            logger.error("deduct inventory incorrect.");
        }
        logger.info("updated rows:{}", affectedRows);
    }

    @Override
    public Storage getStorage(String commodityCode) {
        return this.storageRepository.getStorage(commodityCode);
    }
}
