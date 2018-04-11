package com.zm.controller;

import com.zm.feign.client.ShopFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    ShopFeignClient shopFeignClient;

    @GetMapping
    public String sayHello(){
        return "hello world";
    }

    @GetMapping("/feignShop")
    public String getFeignMsg(){
        return shopFeignClient.sayHello();
    }


}
