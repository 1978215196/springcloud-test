package com.hayzt.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.hayzt.pojo.Man;
import lombok.extern.slf4j.Slf4j;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageLisenter {

    @KafkaListener(topics = {"test"})
//    public void listener(List<ConsumerRecord<String, DeviceLocationBak>> recordList){
    public void listener(ConsumerRecord<String, String> record){
        // 打印消息的分区以及偏移量
        log.info("Kafka Consume prtition:{}, offset:{}", record.partition(), record.offset());

        String obj = record.value();
        Man man = JSONObject.parseObject(obj, Man.class);
        log.info("接收消息：{}", "姓名："+man.getName()+"，年龄："+man.getAge()+"，编号："+man.getNum());
    }

}
