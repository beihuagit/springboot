package com.xiaole.test.service;

import com.xiaole.test.pojo.LoginLog;

public interface LoginLogService {

    void insert(LoginLog loginLog);

    LoginLog selectByMsgId(String msgId);

}
