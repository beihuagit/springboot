package com.xiaole.test.service.impl;

import com.xiaole.test.mq.MessageHelper;
import com.xiaole.test.common.ResponseCode;
import com.xiaole.test.common.ServerResponse;
import com.xiaole.test.config.RabbitDirectQueueConfig;
import com.xiaole.test.mapper.MsgLogMapper;
import com.xiaole.test.pojo.Mail;
import com.xiaole.test.pojo.MsgLog;
import com.xiaole.test.service.TestService;
import com.xiaole.test.util.RandomUtil;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

}
