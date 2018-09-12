package com.cycfc.demo.provider.dao;

import com.cycfc.demo.provider.model.JpaDemo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 描述：TODO<br/>
 *
 * @author Yanzheng 严正<br/>
 * 时间：2018/9/4 0:08<br/>
 * 版权：Copyright 2018 Cycfc. All rights reserved.
 */
public interface JpaDemoDao extends JpaRepository<JpaDemo, String> {

    // 占位符风格
    @Query(value = "SELECT * FROM tb_demo WHERE sex = ?1", nativeQuery = true)
    List<JpaDemo> findBySql(boolean sex);

}
