package com.hayzt.controller;

import lombok.extern.slf4j.Slf4j;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

import javax.annotation.Resource;

@Component
@Slf4j
public class MessageLisenter {

    @KafkaListener(topics = {"test"})
//    public void listener(List<ConsumerRecord<String, DeviceLocationBak>> recordList){
    public void listener(ConsumerRecord<String, String> record){
        // 打印消息的分区以及偏移量
        log.info("Kafka Consume prtition:{}, offset:{}", record.partition(), record.offset());

        String obj = record.value();
        log.info("接收消息：{}", obj);
    }

}
