package demo.aa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import demo.aa.mapper.loginMapper;

@Service
public class loginService {

    @Autowired
    public loginMapper loginMapper;

    public String register(String username,String password){
        loginMapper.save(username,password);
        return "register success";
    }

    public String login(String username, String password) {
        String res = "login fail";

        String ps=loginMapper.findPasswdByUname(username);
        System.out.println(username);
        System.out.println(password);
        System.out.println(ps);
        try {
            if (ps.equals(password)){
                res= "login success";
            }
        }catch (Exception e){
            return res;
        }

        return res;
    }
}
