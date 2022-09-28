package com.hayzt.controller;

import com.alibaba.fastjson2.JSON;
import com.hayzt.pojo.Man;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: yanghao
 * @create: 2022/9/27 10:25
 * @Description:
 */
@RestController
@Slf4j
public class KafkaProducer {
    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    @GetMapping("/kafkaProducer")
    public void sendMessage() throws InterruptedException {
        int num = 0;
        while (true) {
            Thread.sleep(5 * 1000);
            kafkaTemplate.send("test", "发送消息" + num);
            num++;
        }
    }

    @GetMapping("/kafkaProducer2")
    public void sendMessage2() throws InterruptedException {
        int num = 0;
        Man man = new Man();
        man.setName("YH");
        man.setAge(18);
        while (true) {
            man.setNum(num);
            Thread.sleep(5 * 1000);
            kafkaTemplate.send("test", JSON.toJSONString(man));
            num++;
        }
    }
}
