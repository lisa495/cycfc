package com.cycfc.demo.provider.controller;

import com.cycfc.demo.provider.dao.JpaDemoDao;
import com.cycfc.demo.provider.dao.TbDemoCustomMapper;
import com.cycfc.demo.provider.dao.TbDemoMapper;
import com.cycfc.demo.provider.lock.handler.RedisDistributedLockHandler;
import com.cycfc.demo.provider.lock.model.RedisLock;
import com.cycfc.demo.provider.model.JpaDemo;
import com.cycfc.demo.provider.model.TbDemo;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * 描述：TODO<br/>
 *
 * @author Yanzheng 严正<br/>
 * 时间：2018/9/8 15:58<br/>
 * 版权：Copyright 2018 Cycfc. All rights reserved.
 */
@Controller
@Api("ProviderDemo示例代码，包括JPA、Mybatis基本使用")
@Slf4j
public class ProviderDemoController {

    @Value("${foo}")
    String foo;

    @Value("${server.port}")
    String port;

    @Autowired
    private JpaDemoDao jpaDao;

    @Autowired
    private TbDemoMapper mapper;

    @Autowired
    private TbDemoCustomMapper demoCustomMapper;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private RedisDistributedLockHandler redisDistributedLockHandler;

    @ApiOperation(value = "测试配置中心的参数变化")
    @RequestMapping(value = "/foo", method = RequestMethod.GET)
    @ResponseBody
    public String foo() {
        return foo;
    }

    @ApiOperation(value = "初始化到index首页")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("name", "Yanzheng[cycfc-demo-provider]");
        return mv;
    }

    @ApiOperation(value = "hello界面，返回json字符串，标记端口号")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello(@RequestParam(value = "name", defaultValue = "Yanzheng[cycfc-demo-provider]") String name) {
        return "您好：" + name + "，进入cycfc-demo-provider服务，执行ProviderController中的hello方法，端口：" + port;
    }

    @ApiOperation(value = "使用JPA方式插入数据，带事物", notes = "该方法若要触发事物回滚，请输入数量大于10即可")
    @ApiParam(name = "num", value = "插入数据数量", required = true)
    @RequestMapping(value = "/jpa/save/{num}", method = {RequestMethod.GET, RequestMethod.POST})
    @Transactional
    @ResponseBody
    public String jpaSave(@PathVariable int num) {
        if (num <= 0)
            return "参数必须是大于0的数字";
        for (int i = 0; i < num; i++) {

            String name = "随机姓名-" + (int) Math.ceil(Math.random() * 100);
            int age = new Random().nextInt(29) + 1;
            boolean sex = Math.ceil(Math.random() * 10) % 2 == 0 ? true : false;

            if (i < 10) {
                JpaDemo jpaDemo = new JpaDemo(UUID.randomUUID().toString().replaceAll("-", ""), name, age, sex);
                jpaDao.save(jpaDemo);
            } else {
                // 错误数据（事物回滚）
                JpaDemo jpaDemo = new JpaDemo(UUID.randomUUID().toString(), name, age, sex);
                jpaDao.save(jpaDemo);
            }

        }
        return "数据保存成功，条数：" + num;
    }

    @ApiOperation(value = "使用JPA方式自定义SQL查询数据", notes = "有一个性别参数，必填")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sex", value = "性别(0:女,1:男)", dataType = "boolean", required = true)
    })
    @RequestMapping(value = "/jpa/sql/list/{sex}", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<JpaDemo> jpaList(@PathVariable boolean sex) {
        List<JpaDemo> list = jpaDao.findBySql(sex);
        return list;
    }

    @ApiOperation(value = "使用JPA方式分页查询", notes = "分页默认第1页，每页展示6条数据")
    @RequestMapping(value = "/jpa/page/list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Page<JpaDemo> jpaPageList() {
        Sort sort = new Sort(Sort.Direction.DESC, "age");
        Pageable pageable = PageRequest.of(1, 6, sort);
        Page<JpaDemo> page = jpaDao.findAll(pageable);
        return page;
    }

    @ApiOperation(value = "使用Mybatis方式插入数据", notes = "包含事物")
    @RequestMapping(value = "/mybatis/save", method = {RequestMethod.GET, RequestMethod.POST})
    @Transactional
    @ResponseBody
    public String mybatisSave() {

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        TbDemo demo = new TbDemo();
        demo.setId(uuid);
        demo.setName("Provider提供者");
        demo.setAge(1);
        demo.setSex(true);
        mapper.insertSelective(demo);

        return "SUCCESS";
    }

    @ApiOperation("使用mybatis自定义sqlMap查询，并分页")
    @RequestMapping(value = "/mybatis/sql/list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<Object> mybatisSqlList() {
        PageHelper.offsetPage(0, 5);
        List<Object> list = sqlSessionTemplate.selectList("com.cycfc.demo.provider.dao.TbDemoCustomMapper.getAllTbDemo");
        return list;
    }

    @ApiOperation("使用mybatis自定义API接口，注解SQL方式查询")
    @RequestMapping(value = "/mybatis/api/list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<Object> mybatisApiList() {
        List<Object> list = demoCustomMapper.selectAll();
        return list;
    }

    @ApiOperation("使用mybatis generate生成mapper进行分页查询")
    @RequestMapping(value = "/mybatis/mapper/list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<TbDemo> mybatisMapperList() {
        List<TbDemo> list = mapper.selectByExample(null);
        return list;
    }

    @ApiOperation("分布式锁测试，需启动redis服务")
    @RequestMapping(value = "/lock", method = RequestMethod.GET)
    @ResponseBody
    public String lock() {
        RedisLock redisLock = new RedisLock("lockKey", "userId");
        if (redisDistributedLockHandler.tryLock(redisLock)) {
            System.out.println("在这里开始处理操作...");
            redisDistributedLockHandler.releaseLock(redisLock);
        }
        return "SUCCESS";
    }

}
