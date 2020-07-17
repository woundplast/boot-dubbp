package com.boot.dubbo.consumer;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableDubboConfiguration  //开启dubbo的自动配置
@ServletComponentScan(basePackages = {"com.boot.dubbo.consumer.servlet","com.boot.dubbo.consumer.filter"})  //扫描 myServlet包 myFilter包
public class ConsumerApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class,args);

//        关闭 boot logo图标显示
//        SpringApplication springApplication = new SpringApplication(ConsumerApplication.class);
//        springApplication.setBannerMode(Banner.Mode.OFF);
//        springApplication.run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ConsumerApplication.class);
    }
}
