package com.zm.feign.client;

import com.alibaba.fastjson.JSONObject;
import com.zm.feign.hystrix.ShopFeignClientHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.xml.ws.ServiceMode;

/**
 * @author tianshenglong
 * @date 2018/4/11
 * @time 17:19
 */
@Service
//FeignClient 指定调用哪个微服务，fallback  使用断路器
@FeignClient(value = "api-shop", fallback = ShopFeignClientHystrix.class)
public interface ShopFeignClient {

    @GetMapping("/v1/shops")
    String sayHello();
}
