package com.example.demo.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Description: 开启webSocket 支持
 * @Auther: zhangjie
 * @Date: 2018/9/12 15:31
 */
@Configuration
public class WebSocketConfig {

    @Autowired
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

}
