package com.wenting.account.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.wenting.account.dao"})
public class MybatisConfig {
}
