package com.xiaole.dowel.mq.consumer;

import com.rabbitmq.client.Channel;
import com.xiaole.dowel.mq.BaseConsumer;
import com.xiaole.dowel.mq.MessageHelper;
import com.xiaole.dowel.pojo.LoginLog;
import com.xiaole.dowel.service.LoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class LoginLogConsumer implements BaseConsumer {

    @Resource
    private LoginLogService loginLogService;

    @Override
    public void consume(Message message, Channel channel) {
        log.info("收到消息: {}", message.toString());
        LoginLog loginLog = MessageHelper.msgToObj(message, LoginLog.class);
        loginLogService.insert(loginLog);
    }
}
