package com.hayzt.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: yanghao
 * @create: 2022/9/26 9:39
 * @Description:
 */
@FeignClient("gateway-service")
public interface TestService {

    @RequestMapping("/gatewayTest/openfeignTest")
    public String openfeignTest();
}
