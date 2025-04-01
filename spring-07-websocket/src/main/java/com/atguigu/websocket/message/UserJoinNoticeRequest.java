package com.atguigu.websocket.message;

public class UserJoinNoticeRequest implements Message {
    public static final String TYPE = "USER_JOIN_NOTICE_REQUEST";

    /**
     * 昵称
     */
    private String nickname;
    
    // ... 省略 set/get 方法
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
