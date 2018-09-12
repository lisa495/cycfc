package com.cycfc.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 描述：配置中心<br/>
 *
 * @author Yanzheng 严正<br/>
 * 时间：2018/8/29 15:16<br/>
 * 版权：Copyright 2018 Cycfc. All rights reserved.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }

}