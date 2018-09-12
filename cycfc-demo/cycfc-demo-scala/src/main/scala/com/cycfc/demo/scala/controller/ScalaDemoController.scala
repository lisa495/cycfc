package com.cycfc.demo.scala.controller

import java.util

import com.cycfc.demo.scala.dao.ScalaDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{RequestMapping, RequestParam, RestController}

import scala.collection.JavaConverters._
import scala.collection.mutable._

/**
  * 描述：TODO<br/>
  *
  * @author Yanzheng 严正<br/>
  *         时间：2018/9/8 18:50<br/>
  *         版权：Copyright 2018 Cycfc. All rights reserved.
  */
@RestController
class ScalaDemoController {

  @Autowired val scalaDao: ScalaDao = null

  @RequestMapping(Array("/init"))
  def init(): String = "This is cycfc-demo-scala"

  @RequestMapping(Array("/hello"))
  def hello(@RequestParam(value = "name", defaultValue = "Yanzheng[cycfc-demo-scala]") name: String): String = "Hello " + name;

  @RequestMapping(value = Array("/map"))
  def map(): java.util.Map[String, Any] = {
    val res = Map("Java" -> "1.8.0_172", ("Scala", 2.11), "Objective-C" -> "iOS Develop", "Python" -> 3, "Shell" -> "Linux")
    res.put("XXX", 666)
    res("Key") = "Value"
    res.asJava
  }

  @RequestMapping(value = Array("/list"))
  def list(): java.util.List[String] = {
    val res = ListBuffer("Java", "Scala", "Objective-C", "Shell")
    val resEx = ListBuffer("Python", "R")
    res ++= resEx
    res.asJava
  }

  @RequestMapping(value = Array("/getall"))
  def getAll(): java.util.List[util.Map[String, Any]] = {
    val value: util.List[util.Map[String, Any]] = scalaDao.getAll
    value
  }

}
