package com.atguigu.websocket.handler;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.atguigu.websocket.message.AuthRequest;
import com.atguigu.websocket.message.AuthResponse;
import com.atguigu.websocket.message.UserJoinNoticeRequest;
import com.atguigu.websocket.util.WebSocketUtil;

import javax.websocket.Session;

@Component
public class AuthMessageHandler implements MessageHandler<AuthRequest> {

    @Override
    public void execute(Session session, AuthRequest message) {
        // 如果未传递 accessToken 
        if (StringUtils.isEmpty(message.getAccessToken())) {
            AuthResponse response = new AuthResponse();
            response.setCode(1);
            response.setMessage("认证 accessToken 未传入");
            WebSocketUtil.send(session, AuthResponse.TYPE, response);
            return;
        }

        // 添加到 WebSocketUtil 中
        WebSocketUtil.addSession(session, message.getAccessToken()); // 考虑到代码简化，我们先直接使用 accessToken 作为 User

        // 判断是否认证成功。这里，假装直接成功
        AuthResponse authResponse = new AuthResponse();
        authResponse.setCode(0);
        WebSocketUtil.send(session, AuthResponse.TYPE, authResponse);

        // 通知所有人，某个人加入了。这个是可选逻辑，仅仅是为了演示
        UserJoinNoticeRequest noticeRequest = new UserJoinNoticeRequest();
        noticeRequest.setNickname(message.getAccessToken()); // 考虑到代码简化，我们先直接使用 accessToken 作为 User
        WebSocketUtil.broadcast(UserJoinNoticeRequest.TYPE, noticeRequest);
    }

    @Override
    public String getType() {
        return AuthRequest.TYPE;
    }

}
