package com.cycfc.demo.provider.lock.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 描述：TODO<br/>
 *
 * @author Yanzheng 严正<br/>
 * 时间：2018/9/10 15:39<br/>
 * 版权：Copyright 2018 Cycfc. All rights reserved.
 */
@Data
@AllArgsConstructor
public class RedisLock {
    private String name;
    private String value;
}
