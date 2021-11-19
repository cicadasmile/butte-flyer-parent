package com.butte.flyer.admin.controller.kafka;

import com.butte.kafka.entity.SendMsgVO;
import com.butte.kafka.operate.KafkaSendOperate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Kafka组件
 * @author 公众号:知了一笑
 * @since 2021-10-31 16:34
 */
@RestController
@RequestMapping("/kafka")
public class KafkaWeb {

    @Resource
    private KafkaSendOperate sendOperate ;

    /**
     * Kafka消息发送
     * @since 2021-11-06 16:48
     */
    @GetMapping("/sendMsg")
    public String sendMsg () {
        SendMsgVO sendMsgVO = new SendMsgVO() ;
        sendMsgVO.setTopic(KafkaTopic.USER_TOPIC);
        sendMsgVO.setKey(KafkaTopic.USER_INSERT_KEY);
        sendMsgVO.setMsgBody("userId:1");
        sendOperate.send(sendMsgVO);
        return sendMsgVO.getTopic() ;
    }
}
