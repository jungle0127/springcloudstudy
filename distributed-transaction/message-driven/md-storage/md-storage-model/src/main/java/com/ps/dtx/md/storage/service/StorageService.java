package com.ps.dtx.md.storage.service;

import com.ps.dtx.md.storage.model.Storage;

public interface StorageService {
    /**
     * deduct storage count
     */
    void deduct(Storage storage);
}
