package com.hayzt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yanghao
 * @create: 2022/9/23 16:07
 * @Description:
 */
@RestController
@RequestMapping("/gatewayTest")
public class GatewayTestController {

    @RequestMapping("/test1")
    public String test1() {
        return "恭喜恭喜";
    }

    @RequestMapping("/openfeignTest")
    public String openfeignTest() {
        return "恭喜恭喜openfeignTest";
    }

}
