package com.cycfc.demo.consumer.client;

import com.cycfc.demo.consumer.client.hystrix.ProviderDemoClientHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 描述：TODO<br/>
 *
 * @author Yanzheng 严正<br/>
 * 时间：2018/8/31 12:55<br/>
 * 版权：Copyright 2018 Cycfc. All rights reserved.
 */
@FeignClient(value = "cycfc-demo-provider", fallback = ProviderDemoClientHystrix.class)
public interface ProviderDemoClientFeign {

    @RequestMapping("/")
    String index();

    @RequestMapping("/hello")
    String sayHello(@RequestParam(name = "name", defaultValue = "cycfc-demo-consumer[client feign]") String name);

    @RequestMapping("/jpa/save/{num}")
    String jpaSave(@PathVariable(value = "num") int num);

    @RequestMapping("/mybatis/save")
    String mybatisSave();

}
