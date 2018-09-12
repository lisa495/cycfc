package com.cycfc.demo.provider.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述：TODO<br/>
 *
 * @author Yanzheng 严正<br/>
 * 时间：2018/9/7 16:52<br/>
 * 版权：Copyright 2018 Cycfc. All rights reserved.
 */
@Service("demoCustomMapper")
public interface TbDemoCustomMapper {

    @Select("SELECT * FROM tb_demo")
    List<Object> selectAll();

}
