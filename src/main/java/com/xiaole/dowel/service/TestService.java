package com.xiaole.dowel.service;

import com.xiaole.dowel.common.ServerResponse;
import com.xiaole.dowel.pojo.Mail;

public interface TestService {

    ServerResponse testIdempotence();

    ServerResponse accessLimit();

    ServerResponse send(Mail mail);

    /**
     * 功能描述：推送主题消息
     * @author 陈小乐 xiaole_chen@aliyun.com
     * @date 2020-09-09
     */
    ServerResponse sendTopic(String exchangeName, String routingKey, String message);
}
