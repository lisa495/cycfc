package com.cycfc.demo.scala

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.hystrix.EnableHystrix

/**
  * 描述：TODO<br/>
  *
  * @author Yanzheng 严正<br/>
  *         时间：2018/9/8 19:02<br/>
  *         版权：Copyright 2018 Cycfc. All rights reserved.
  */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableCircuitBreaker
@MapperScan(Array("com.cycfc.demo.scala.dao"))
class ScalaApplication {

}
