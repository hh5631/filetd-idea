package demo.aa.controller;

import demo.aa.entity.webSocket;
import demo.aa.websocket.webSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class webSocketController {
    @Autowired
    public webSocketService websocketService;

    @GetMapping("/getAllMessage")
    public String getAllMessage(){

        return  websocketService.getAllMessage();
    }
}
