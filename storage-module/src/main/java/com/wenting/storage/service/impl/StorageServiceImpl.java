package com.wenting.storage.service.impl;

import com.wenting.storage.dao.StorageDao;
import com.wenting.storage.service.StorageService;
import org.apache.commons.logging.Log;
import org.mybatis.logging.LoggerFactory;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;

@Service
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageDao storageDao;

    private static final Log logger = LogFactory.getLog(StorageServiceImpl.class);


    @Override
    public void decrease(Long productId, Integer count) {
        logger.info("------------> [扣库存]");
        storageDao.decrease(productId, count);
        logger.info("------------> [扣库存]--------end");
    }
}
