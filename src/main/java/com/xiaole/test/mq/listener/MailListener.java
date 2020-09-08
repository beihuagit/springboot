package com.xiaole.test.mq.listener;

import com.rabbitmq.client.Channel;
import com.xiaole.test.config.RabbitDirectQueueConfig;
import com.xiaole.test.mq.BaseConsumer;
import com.xiaole.test.mq.BaseConsumerProxy;
import com.xiaole.test.mq.consumer.MailConsumer;
import com.xiaole.test.service.MsgLogService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
/**
 * 功能描述：消息队列监听组件
 * @author 陈小乐 xiaole_chen@aliyun.com
 * @date 2020-09-08
 */
@Component
public class MailListener {

    @Resource
    private MailConsumer mailConsumer;

    @Resource
    private MsgLogService msgLogService;

    @RabbitListener(queues = RabbitDirectQueueConfig.MAIL_QUEUE_NAME)
    public void consume(Message message, Channel channel) throws IOException {
        BaseConsumerProxy baseConsumerProxy = new BaseConsumerProxy(mailConsumer, msgLogService);
        BaseConsumer proxy = (BaseConsumer) baseConsumerProxy.getProxy();
        if (null != proxy) {
            proxy.consume(message, channel);
        }
    }

}
