package com.wenting.storage.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.wenting.storage.dao"})
public class MybatisConfig {
}
