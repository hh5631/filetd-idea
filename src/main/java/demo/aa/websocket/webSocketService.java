package demo.aa.websocket;

import com.google.gson.Gson;
import demo.aa.entity.message;
import demo.aa.entity.webSocket;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Component
@ServerEndpoint("/websocket/{name}")
@Service
public class webSocketService {

    message mess = new message();
    public static final Map<Session,String> sessionMap = new HashMap<>();


    public static final List<webSocket> list = new ArrayList<>();

    @OnOpen
    public void OnOpen(Session session,@PathParam(value = "name") String name){

        sessionMap.put(session,name);
        mess.setType("message");
        mess.setData("欢迎"+name+"进入聊天室");
        broadcastAllUsers(new Gson().toJson(mess));
        mess.setType("online");
        mess.setData(new Gson().toJson(sessionMap.values()));
        broadcastAllUsers(new Gson().toJson(mess));
    }
    @OnMessage
    public void  OnMessage(@PathParam(value = "name") String name, String message){
        webSocket messageItem = new webSocket();
        messageItem.name =name;
        messageItem.message=message;
        list.add(messageItem);
        mess.setType("message");
        mess.setData(name+" :    "+message);
        broadcastAllUsers(new Gson().toJson(mess));
    }


    private void broadcastAllUsers(String message){

        for(Session session:sessionMap.keySet()){
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @OnClose
    public void OnClose(Session session){
        sessionMap.remove(session);

    }


    public String getAllMessage() {
       return new Gson().toJson(list);
    }

    public String getOnlineUser() {
        return new Gson().toJson(sessionMap.values());
    }
}
