package com.ps.dtx.md.storage.service;

public interface StorageService {
    /**
     * deduct storage count
     */
    void deduct(String commodityCode, int count);
}
