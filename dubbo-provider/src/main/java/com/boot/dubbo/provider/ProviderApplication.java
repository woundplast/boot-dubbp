package com.boot.dubbo.provider;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages = "com.boot.dubbo.provider.mapper") //mapper 包扫描
@EnableTransactionManagement //开启事务
@EnableDubboConfiguration  //开启dubbo的自动配置
public class ProviderApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ProviderApplication.class);
    }
}

