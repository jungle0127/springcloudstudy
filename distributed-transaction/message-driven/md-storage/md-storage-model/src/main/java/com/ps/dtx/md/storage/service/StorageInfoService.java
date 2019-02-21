package com.ps.dtx.md.storage.service;

import com.ps.dtx.md.storage.model.Storage;

public interface StorageInfoService {
    Storage getStorage(Integer id);

    Storage getStorage(String commodityCode);

    Boolean addStorage(Storage storage);
}
