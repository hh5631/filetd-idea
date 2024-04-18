package demo.aa.websocket;

import com.google.gson.Gson;
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

    public static final Map<Session,String> sessionMap = new HashMap<>();


    public static final List<webSocket> list = new ArrayList<>();

    @OnOpen
    public void OnOpen(Session session,@PathParam(value = "name") String name){

        sessionMap.put(session,name);

    }
    @OnMessage
    public void  OnMessage(@PathParam(value = "name") String name, String message){
        webSocket messageItem = new webSocket();
        messageItem.name =name;
        messageItem.message=message;
        list.add(messageItem);
        broadcastAllUsers(name+" :    "+message);
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
        System.out.println(list);
        System.out.println(list.get(0).name+list.get(0).message);
       return new Gson().toJson(list);
    }
}
