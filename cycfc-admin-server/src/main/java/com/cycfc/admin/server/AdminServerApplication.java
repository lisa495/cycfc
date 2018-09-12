package com.cycfc.admin.server;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 描述：服务监控<br/>
 *
 * @author Yanzheng 严正<br/>
 * 时间：2018/8/28 22:07<br/>
 * 版权：Copyright 2018 Cycfc. All rights reserved.
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
public class AdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminServerApplication.class, args);
    }

}
