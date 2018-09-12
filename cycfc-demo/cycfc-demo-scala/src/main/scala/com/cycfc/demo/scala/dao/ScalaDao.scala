package com.cycfc.demo.scala.dao

import org.apache.ibatis.annotations.Select

/**
  * 描述：TODO<br/>
  *
  * @author Yanzheng 严正<br/>
  *         时间：2018/9/9 14:11<br/>
  *         版权：Copyright 2018 Cycfc. All rights reserved.
  */
trait ScalaDao {

  @Select(Array("SELECT * FROM tb_demo"))
  def getAll: java.util.List[java.util.Map[String, Any]]

}
