package com.example.redisdemo.redis.controller;

import com.example.redisdemo.redis.service.RedisTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/redis")
public class RedisTemplateController {
    @Autowired
    RedisTemplateService redisTemplateService;

    @GetMapping(value = "/string")
    public String redisStringTypeTest() {
        redisTemplateService.setStrings();

        return "sent redis cmd.";
    }

    @GetMapping(value = "/lstring")
    public String redisLongStringTypeTest() {
        redisTemplateService.setLongStrings();

        return "sent redis cmd.";
    }
    @GetMapping(value = "/sets")
    public String redisSetTypeTest() {
        redisTemplateService.cmdSets();

        return "sent redis cmd.";
    }
}