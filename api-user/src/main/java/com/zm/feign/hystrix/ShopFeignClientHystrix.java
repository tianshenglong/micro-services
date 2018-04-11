package com.zm.feign.hystrix;

import com.alibaba.fastjson.JSONObject;
import com.zm.feign.client.ShopFeignClient;
import org.springframework.stereotype.Component;

/**
 * @author tianshenglong
 * @date 2018/4/11
 * @time 17:19
 */
@Component
public class ShopFeignClientHystrix implements ShopFeignClient {

    @Override
    public String sayHello() {
        return "api-shop 服务挂了，请检查";
    }
}
