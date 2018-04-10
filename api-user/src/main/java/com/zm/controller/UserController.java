package com.zm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tianshenglong
 * @date 2018/4/10
 * @time 19:17
 */
@RestController
@RequestMapping("/v1/users")  // rest 命名风格
public class UserController {

    @GetMapping
    public String sayHello(){
        return "hello world";
    }
}
