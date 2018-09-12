package com.cycfc.demo.ribbon.controller;

import com.cycfc.common.bean.RestAPIResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 描述：TODO<br/>
 *
 * @author Yanzheng 严正<br/>
 * 时间：2018/9/8 17:40<br/>
 * 版权：Copyright 2018 Cycfc. All rights reserved.
 */
@RestController
public class RibbonDemoController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/init")
    @HystrixCommand(fallbackMethod = "initHystrix")
    public RestAPIResult<String> init(@RequestParam(name = "name", defaultValue = "Yanzheng[cycfc-demo-ribbon]") String name) {
        RestAPIResult<String> apiResult = new RestAPIResult<>();
        String result = restTemplate.getForObject("http://CYCFC-DEMO-PROVIDER/hello", String.class);
        apiResult.setResData(result);
        return apiResult;
    }

    public RestAPIResult<String> initHystrix(String name) {
        RestAPIResult<String> apiResult = new RestAPIResult<>();
        String result = "进入cycfc-demo-ribbon服务，进入断路器initHystrix...";
        apiResult.setResData(result);
        return apiResult;
    }

}
