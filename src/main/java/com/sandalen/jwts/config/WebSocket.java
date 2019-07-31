package com.sandalen.jwts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@ServerEndpoint(value = "/ws/webSocket")
public class WebSocket {
    private Session session;

    private static CopyOnWriteArrayList<WebSocket> copyOnWriteArrayList = new CopyOnWriteArrayList<>();

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        copyOnWriteArrayList.add(this);
        System.out.println("有新的客户端 sessionId is "+session.getId()+"加入,连接数为"+copyOnWriteArrayList.size());
        System.out.println(session.getBasicRemote());
    }

    @OnClose
    public void onClose(){
        copyOnWriteArrayList.remove(this);
        System.out.println("有客户端断开,连接数为"+copyOnWriteArrayList.size());
    }

    @OnMessage
    public void onMessage(String message){
        System.out.println("websocket接收到消息"+message);
    }

    @OnError
    public void onError(Session session,Throwable error){
        System.out.println("发生错误"+error.getMessage() + "; sessionId is " + session.getId());
        error.printStackTrace();
    }

    public void sendMessage(Object object){
        for(WebSocket webSocket:copyOnWriteArrayList){
            System.out.println("websocket 广播消息"+object.toString());
            try {
                webSocket.session.getBasicRemote().sendObject(object);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message){
        for(WebSocket webSocket:copyOnWriteArrayList){
            System.out.println("websocket 广播消息"+message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String sessionId,String message) throws IOException {
        Session session = null;
        WebSocket tempWebSocket = null;
        for(WebSocket webSocket : copyOnWriteArrayList){
            if(webSocket.session.getId().equals(sessionId)){
                tempWebSocket = webSocket;
                session = webSocket.session;
                break;
            }
        }

        if(session != null){
            tempWebSocket.session.getBasicRemote().sendText(message);
        }else {
            System.out.println("没有找到指定ID的会话");
        }
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
