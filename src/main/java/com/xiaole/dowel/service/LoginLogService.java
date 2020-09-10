package com.xiaole.dowel.service;

import com.xiaole.dowel.pojo.LoginLog;

public interface LoginLogService {

    void insert(LoginLog loginLog);

    LoginLog selectByMsgId(String msgId);

}
