package com.zhxg.fgw.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 仅是一个测试类
 */
@RestController
@RequestMapping("/api/test")
public class TmpTestController {
    @Resource
    private JdbcTemplate jdbcTemplate;
//    @Autowired
//    private Pojo1 pojo1;

    @RequestMapping("hello")
    public String hello() {
//        System.out.println(pojo1.getList());
        return "hello world";
    }

    @RequestMapping
    public String dbTest1() {
        jdbcTemplate.queryForObject("select count(*) from WK_T_USER", Long.class);
        return "db-test1: ok" + System.currentTimeMillis();
    }
}
