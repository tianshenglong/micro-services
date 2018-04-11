package com.zm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tianshenglong
 * @date 2018/4/11
 * @time 16:32
 */
@RestController
@RequestMapping("/v1/shops")
public class ShopController {

    @GetMapping
    public String sayHello(){
        return "api-shop 通过feign调用成功了";
    }
}
