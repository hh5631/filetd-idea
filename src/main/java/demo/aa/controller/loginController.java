package demo.aa.controller;

import demo.aa.service.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class loginController {
    @Autowired
    private loginService loginService;
    @GetMapping("/register")
    public String register(String username,String password){

        return loginService.register(username, password);
    }

    @GetMapping("/login")
    public String login(String username,String password){

        return loginService.login(username, password);
    }


}
