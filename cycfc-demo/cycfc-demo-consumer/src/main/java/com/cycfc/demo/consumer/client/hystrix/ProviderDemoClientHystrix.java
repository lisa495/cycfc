package com.cycfc.demo.consumer.client.hystrix;

import com.cycfc.demo.consumer.client.ProviderDemoClientFeign;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 描述：TODO<br/>
 *
 * @author Yanzheng 严正<br/>
 * 时间：2018/8/31 12:56<br/>
 * 版权：Copyright 2018 Cycfc. All rights reserved.
 */
@Component
public class ProviderDemoClientHystrix implements ProviderDemoClientFeign {

    @Value("${server.port}")
    String port;

    @Override
    public String index() {
        return "cycfc-demo-consumer服务，进入断路器index...，端口：" + port;
    }

    @Override
    public String sayHello(String name) {
        return "cycfc-demo-consumer服务，进入断路器sayHello...，参数：name = " + name + " 端口：" + port;
    }

    @Override
    public String jpaSave(int num) {
        return "cycfc-demo-consumer服务，进入断路器jpaSave...，参数：num = " + num + "，端口：" + port;
    }

    @Override
    public String mybatisSave() {
        return "cycfc-demo-consumer服务，进入断路器mybatisSave...，端口：" + port;
    }

}
