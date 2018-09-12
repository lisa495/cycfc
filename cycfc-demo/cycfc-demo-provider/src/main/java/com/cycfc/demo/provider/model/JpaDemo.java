package com.cycfc.demo.provider.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 描述：TODO<br/>
 *
 * @author Yanzheng 严正<br/>
 * 时间：2018/9/4 23:59<br/>
 * 版权：Copyright 2018 Cycfc. All rights reserved.
 */
@Entity
@Data
@Table(name = "tb_demo")
@NoArgsConstructor
@AllArgsConstructor
public class JpaDemo implements Serializable {

    @Id
    @Column(nullable = false, length = 32)
    private String id;
    @Column(nullable = false, length = 36)
    private String name;
    @Column
    private int age;
    @Column
    private boolean sex;

}
