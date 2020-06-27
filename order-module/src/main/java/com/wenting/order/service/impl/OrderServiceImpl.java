package com.wenting.order.service.impl;

import com.wenting.order.dao.OrderDao;
import com.wenting.order.domain.Order;
import com.wenting.order.feignclient.AccountFeign;
import com.wenting.order.feignclient.StorageFeign;
import com.wenting.order.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private static final Log logger = LogFactory.getLog(OrderServiceImpl.class);

    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageFeign storageFeign;
    @Resource
    private AccountFeign accountFeign;

    // 下订单
    @GlobalTransactional(name = "fsp-create-order", rollbackFor = Exception.class)
    public void create(Order order) {

        logger.info("------------> [创建订单]");
        orderDao.create(order);

        logger.info("------------> [订单微服务调用库存微服务,扣库存]");
        storageFeign.decrease(order.getProductId(), order.getCount());
        logger.info("------------> [订单微服务调用库存微服务,扣库存 ---- end]");

        logger.info("------------> [订单微服务调用账户微服务,扣钱]");
        accountFeign.decrease(order.getUserId(), order.getMoney());
        logger.info("------------> [订单微服务调用账户微服务,扣钱 ----end]");

        // 4. 修改订单状态  0 -> 1 代表已完成
        logger.info("---------->[修改订单状态 ****]");
        orderDao.update(order.getUserId(), 0);
        logger.info("---------->[修改订单状态 **** end]");

        logger.info("[---------下订单结束-------]");

    }

}
