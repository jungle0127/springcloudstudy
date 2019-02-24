package com.ps.dtx.fd.storage.service;

import com.ps.dtx.fd.storage.model.Storage;

public interface StorageService {
    /**
     * deduct storage count
     */
    void deduct(String commodityCode, int count);

    Storage getStorage(String commodityCode);

}
