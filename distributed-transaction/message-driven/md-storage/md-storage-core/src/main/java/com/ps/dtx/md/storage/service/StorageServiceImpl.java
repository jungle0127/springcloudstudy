package com.ps.dtx.md.storage.service;

import com.ps.dtx.md.storage.dao.repository.StorageRepository;
import com.ps.dtx.md.storage.model.Storage;
import com.ps.dtx.md.storage.model.StorageJmsDestinationConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StorageServiceImpl implements StorageService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private StorageRepository storageRepository;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Override
    @JmsListener(destination = StorageJmsDestinationConstant.STORAGE_CREATE_DESTINATION,containerFactory = "containerFactory")
    @Transactional
    public void deduct(Storage storage) {
        Integer affectedRows = this.storageRepository.updateInventory(storage.getCommodityCode(),storage.getCount());
        logger.info("deduct storage affected rows:{}", affectedRows);
    }
}
