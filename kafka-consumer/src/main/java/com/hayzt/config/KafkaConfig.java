package com.hayzt.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@Slf4j
@Data
public class KafkaConfig {

    private static final Integer CONSUMER_CONFIGS_COUNT = 10;
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

//    @Value("#{'${kafka.consumer.cp}'.split(',')}")
//    private List<String> cpList;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;
    
    @Value("${spring.kafka.consumer.enable-auto-commit}")
    private boolean autoCommit;

    @Value("${spring.kafka.consumer.auto-commit-interval}")
    private Integer autoCommitIntervalMs;
    
    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;

    @Value("${spring.kafka.consumer.key-deserializer}")
    private String keyDeserializer;
    
    @Value("${spring.kafka.consumer.value-deserializer}")
    private String valueDeserializer;
    
    @Value("${spring.kafka.consumer.security-status}")
    private boolean securityStatus;
    
    @Value("${spring.kafka.consumer.username}")
    private String username;

    @Value("${spring.kafka.consumer.password}")
    private String password;

//    @Value("${kafka.consumer.batch-listener}")
//    private boolean batchListener;
//
//    @Value("${kafka.consumer.max-poll-records}")
//    private Integer maxPollRecords;
//
//    @Value("${kafka.consumer.fetch-min-bytes}")
//    private Integer fetchMinBytes;
//
//    @Value("${kafka.consumer.fetch-max-wait-ms}")
//    private Integer fetchMaxWaitMs;
//
//    @Value("${kafka.consumer.max-poll-time-out}")
//    private Integer maxPollTimeOut;
//
//    @Value("${kafka.consumer.session-time-out-ms}")
//    private Integer sessionTimeOutMs;
//

//
////    @Value("${kafka.consumer.partition-assignment-strategy}")
////    private String partitionAssignor;
//
//    @Value("${kafka.consumer.security.protocol}")
//    private String protocol;
//
//    @Value("${kafka.consumer.security.sasl-mechanism}")
//    private String mechanism;


//    private static final String P_NAME = "password";

//    public KafkaConsumerConfig() { log.info("Kafka消费者配置加载..."); }


    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, String>>
    kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(CONSUMER_CONFIGS_COUNT);
        //factory.setBatchListener(Boolean.TRUE);
        factory.getContainerProperties().setPollTimeout(500);
        return factory;
    }

    @Bean
    public ConsumerFactory<Integer, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, autoCommit);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitIntervalMs);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializer);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializer);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        if(securityStatus) {
            props.put(AdminClientConfig.SECURITY_PROTOCOL_CONFIG, "SASL_PLAINTEXT");
            props.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
            props.put(SaslConfigs.SASL_JAAS_CONFIG, 
            		"org.apache.kafka.common.security.plain.PlainLoginModule required username=\""+username+"\" password=\""+password+"\";");
        }
//            props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecords);
//            props.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, fetchMinBytes);
//            props.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, fetchMaxWaitMs);
//            props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, maxPollTimeOut);
//            props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, sessionTimeOutMs);
//            props.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, partitionAssignor);
        return props;
    }
}
