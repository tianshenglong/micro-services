package com.zm.controller;

import com.zm.feign.client.ShopFeignClient;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "简单输出你好",notes = "简单输出你好，用于测试")
    //@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")//指明参数
    @GetMapping
    public String sayHello(){
        return "hello world";
    }

    @GetMapping("/feignShop")
    public String getFeignMsg(){
        return shopFeignClient.sayHello();
    }


}
