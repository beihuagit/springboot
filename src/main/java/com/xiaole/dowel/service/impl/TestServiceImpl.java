package com.xiaole.dowel.service.impl;

import com.xiaole.dowel.mq.MessageHelper;
import com.xiaole.dowel.common.ResponseCode;
import com.xiaole.dowel.common.ServerResponse;
import com.xiaole.dowel.config.RabbitDirectQueueConfig;
import com.xiaole.dowel.mapper.MsgLogMapper;
import com.xiaole.dowel.pojo.Mail;
import com.xiaole.dowel.pojo.MsgLog;
import com.xiaole.dowel.service.TestService;
import com.xiaole.dowel.util.RandomUtil;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class TestServiceImpl implements TestService {

    @Resource
    private MsgLogMapper msgLogMapper;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public ServerResponse testIdempotence() {
        return ServerResponse.success("testIdempotence: success");
    }

    @Override
    public ServerResponse accessLimit() {
        return ServerResponse.success("accessLimit: success");
    }

    @Override
    public ServerResponse send(Mail mail) {
        String msgId = RandomUtil.UUID32();
        mail.setMsgId(msgId);

        MsgLog msgLog = new MsgLog(msgId, mail, RabbitDirectQueueConfig.MAIL_EXCHANGE_NAME, RabbitDirectQueueConfig.MAIL_ROUTING_KEY_NAME);
        // 消息入库
        msgLogMapper.insert(msgLog);

        CorrelationData correlationData = new CorrelationData(msgId);
        // 发送消息
        rabbitTemplate.convertAndSend(RabbitDirectQueueConfig.MAIL_EXCHANGE_NAME, RabbitDirectQueueConfig.MAIL_ROUTING_KEY_NAME, MessageHelper.objToMsg(mail), correlationData);

        return ServerResponse.success(ResponseCode.MAIL_SEND_SUCCESS.getMsg());
    }

    /**
     * 功能描述：推送主题消息
     * @author 陈小乐 xiaole_chen@aliyun.com
     * @date 2020-09-09
     */
    @Override
    public ServerResponse sendTopic(String exchangeName, String routingKey, String message) {
        String messageId = RandomUtil.UUID32();
        String messageData = "message: "+ message;
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> womanMap = new HashMap<>(16);
        womanMap.put("messageId", messageId);
        womanMap.put("messageData", messageData);
        womanMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend("topicExchange", "topic.woman", womanMap);
        return ServerResponse.success(ResponseCode.SUCCESS.getMsg());
    }
}
