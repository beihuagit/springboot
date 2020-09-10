package com.xiaole.dowel.mq.consumer;

import com.rabbitmq.client.Channel;
import com.xiaole.dowel.exception.ServiceException;
import com.xiaole.dowel.mq.BaseConsumer;
import com.xiaole.dowel.mq.MessageHelper;
import com.xiaole.dowel.pojo.Mail;
import com.xiaole.dowel.util.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 消息消费者
 * @author chenxl
 */
@Component
@Slf4j
public class MailConsumer implements BaseConsumer {

    @Resource
    private MailUtil mailUtil;

    @Override
    public void consume(Message message, Channel channel) {
        Mail mail = MessageHelper.msgToObj(message, Mail.class);
        log.info("收到消息: {}", mail.toString());
        // 收到消息后，具体的业务处理
        boolean success = mailUtil.send(mail);
        if (!success) {
            throw new ServiceException("send mail error");
        }
    }

}
