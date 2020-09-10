package com.xiaole.dowel.mapper;

import com.xiaole.dowel.pojo.LoginLog;

public interface LoginLogMapper {

    void insert(LoginLog loginLog);

    LoginLog selectByMsgId(String msgId);

}
