package com.ps.dtx.fd.storage.service;

public interface StorageService {
    /**
     * deduct storage count
     */
    void deduct(String commodityCode, int count);
}
