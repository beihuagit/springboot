package com.xiaole.test.service;

import com.xiaole.test.common.ServerResponse;
import com.xiaole.test.pojo.Mail;

public interface TestService {

    ServerResponse testIdempotence();

    ServerResponse accessLimit();

    ServerResponse send(Mail mail);
}
