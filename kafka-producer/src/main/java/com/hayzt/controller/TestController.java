package com.hayzt.controller;

import com.hayzt.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: yanghao
 * @create: 2022/9/23 16:07
 * @Description:
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/test1")
    public String test1() {
        return "恭喜恭喜123";
    }

}
