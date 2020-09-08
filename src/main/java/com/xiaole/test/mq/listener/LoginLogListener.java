package com.xiaole.test.mq.listener;

import com.rabbitmq.client.Channel;
import com.xiaole.test.config.RabbitDirectQueueConfig;
import com.xiaole.test.mq.BaseConsumer;
import com.xiaole.test.mq.BaseConsumerProxy;
import com.xiaole.test.mq.consumer.LoginLogConsumer;
import com.xiaole.test.service.MsgLogService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

@Component
public class LoginLogListener {

    @Resource
    private LoginLogConsumer loginLogConsumer;

    @Resource
    private MsgLogService msgLogService;

    @RabbitListener(queues = RabbitDirectQueueConfig.LOGIN_LOG_QUEUE_NAME)
    public void consume(Message message, Channel channel) throws IOException {
        BaseConsumerProxy baseConsumerProxy = new BaseConsumerProxy(loginLogConsumer, msgLogService);
        BaseConsumer proxy = (BaseConsumer) baseConsumerProxy.getProxy();
        if (null != proxy) {
            proxy.consume(message, channel);
        }
    }

}
