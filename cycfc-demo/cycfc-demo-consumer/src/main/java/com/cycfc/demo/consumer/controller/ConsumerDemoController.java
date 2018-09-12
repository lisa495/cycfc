package com.cycfc.demo.consumer.controller;

import com.cycfc.demo.consumer.client.ProviderDemoClientFeign;
import com.cycfc.demo.consumer.dao.TbDemoMapper;
import com.cycfc.demo.consumer.model.TbDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 描述：TODO<br/>
 *
 * @author Yanzheng 严正<br/>
 * 时间：2018/9/8 16:28<br/>
 * 版权：Copyright 2018 Cycfc. All rights reserved.
 */
@RestController
@RefreshScope
public class ConsumerDemoController {

    @Value("${foo}")
    String foo;

    @Value("${server.port}")
    String port;

    @Autowired
    ProviderDemoClientFeign providerDemoClientFeign;

    @Autowired
    private TbDemoMapper mapper;

    @RequestMapping("/foo")
    public String foo() {
        return "foo = " + foo;
    }

    @RequestMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "Yanzheng[cycfc-demo-consumer]") String name) {
        return "Hello " + name + " from port : " + port;
    }

    @RequestMapping("/")
    public String index() {
        return providerDemoClientFeign.index();
    }

    @RequestMapping("/hi")
    public String hi(@RequestParam(value = "name", defaultValue = "Yanzheng[cycfc-demo-consumer]") String name) {
        return providerDemoClientFeign.sayHello(name);
    }

    @RequestMapping("/save")
    @Transactional
    public String save() {

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        TbDemo demo = new TbDemo();
        demo.setId(uuid);
        demo.setName("Consumer消费者");
        demo.setAge(9);
        demo.setSex(false);
        mapper.insertSelective(demo);

        return "SUCCESS";
    }

}