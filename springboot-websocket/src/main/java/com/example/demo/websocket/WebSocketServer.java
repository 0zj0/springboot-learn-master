package com.example.demo.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description:
 * @Auther: zhangjie
 * @Date: 2018/9/1215:34
 */
@ServerEndpoint(value = "/websocket/test/{webSocketKey}")
@Component
public class WebSocketServer {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    private static Map<String, WebSocketServer> clients = new ConcurrentHashMap<String, WebSocketServer>();

    private Session session;
    //接收webSocketKey
    private String webSocketKey="";

    /**
     * 连接建立成功调用的方法
     * */
    @OnOpen
    public void onOpen(@PathParam("webSocketKey") String webSocketKey, Session session) {
        this.webSocketKey = webSocketKey;
        this.session = session;
        clients.put(webSocketKey, this);
        logger.info("key:{}",webSocketKey);
        try {
            sendMessage("连接成功",webSocketKey);
        } catch (IOException e) {
            logger.error("websocket IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        clients.remove(webSocketKey);
    }

    /**
     * 收到客户端消息后调用的方法
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("收到来自窗口"+webSocketKey+"的信息:"+message);
    }

    /**
     * 自定义发送消息
     */
    public static void sendMessage(String message, String webSocketKey) throws IOException {
        WebSocketServer item=clients.get(webSocketKey);
        if(item!=null){
            item.session.getAsyncRemote().sendText(message);
        }
    }

    /*
    * 连接错误
    * */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("服务端发生了错误" + error.getMessage());
        error.printStackTrace();
    }

}
