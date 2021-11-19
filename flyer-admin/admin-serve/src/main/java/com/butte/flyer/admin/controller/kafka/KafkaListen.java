package com.butte.flyer.admin.controller.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * Kafka监听
 * @author 公众号:知了一笑
 * @since 2021-10-31 16:34
 */
@Component
public class KafkaListen {

    private static final Logger logger = LoggerFactory.getLogger(KafkaListen.class);

    /**
     * Kafka消息监听
     * @since 2021-11-06 16:47
     */
    @KafkaListener(topics = KafkaTopic.USER_TOPIC)
    public void listenUser (ConsumerRecord<?,String> record, Acknowledgment acknowledgment) {
        try {
            String key =  String.valueOf(record.key());
            String body = record.value();
            switch (key){
                case KafkaTopic.USER_INSERT_KEY :
                    logger.info("key:{} , body:{}",key,body);
                    break ;
                default:
                    logger.info("key:{} mismatch",key);
                    break ;
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            acknowledgment.acknowledge();
        }
    }
}
