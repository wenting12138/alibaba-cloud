package com.wenting.account.service.impl;

import com.wenting.account.dao.AccountDao;
import com.wenting.account.service.AccountService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;

    private static final Log logger = LogFactory.getLog(AccountServiceImpl.class);

    @Override
    public void decrease(Long userId, BigDecimal money) {
        logger.info("[******扣款**********]");
        accountDao.decrease(userId, money);
        logger.info("[******扣款**********end]");
    }
}
