package com.jyl.service.impl;

import com.jyl.dao.StorageDao;
import com.jyl.service.StorageService;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;


@Service
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        System.out.println("----------------->这是库存模块，扣减库存开始");
        storageDao.decrease(productId, count);
        System.out.println("----------------->这是库存模块，扣减库存结束");
    }

}
