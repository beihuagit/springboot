package com.xiaole.test.mapper;

import com.xiaole.test.pojo.LoginLog;

public interface LoginLogMapper {

    void insert(LoginLog loginLog);

    LoginLog selectByMsgId(String msgId);

}
