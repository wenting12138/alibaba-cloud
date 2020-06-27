package com.wenting.storage.service;

import org.apache.ibatis.annotations.Param;

public interface StorageService {

    void decrease(Long productId, Integer count);

}
