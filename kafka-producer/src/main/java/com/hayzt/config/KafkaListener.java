package com.hayzt.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author: yanghao
 * @create: 2022/9/27 10:58
 * @Description:
 */
@Configuration
@Slf4j
public class KafkaListener {

    @Resource
    KafkaTemplate kafkaTemplate;

    //配置监听
    @PostConstruct
    private void listener() {
        kafkaTemplate.setProducerListener(new ProducerListener<String, Object>() {
            @Override
            public void onSuccess(ProducerRecord<String, Object> producerRecord, RecordMetadata recordMetadata) {
                log.info("ok,message={}", producerRecord.value());
            }

            @Override
            public void onError(ProducerRecord<String, Object> producerRecord, Exception exception) {
                log.error("error!message={}", producerRecord.value());
            }
        });
    }
}
