package com.neuq.flight.grab.config;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * author: xiaoliufu
 * date:   2017/5/7.
 * description:
 */
@Slf4j
@EnableTransactionManagement
@Configuration
@MapperScan(basePackages = "com.neuq.flight.grab.mapper")
public class DBConfig {
}
