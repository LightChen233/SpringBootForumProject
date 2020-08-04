package com.hit.controller;

import com.hit.mapper.UserMapper;
import com.hit.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {
    @Autowired
    UserMapper userMapper;
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register";
    }
    @RequestMapping("/register")
    public String register(String username, String pwd, int gender, String email, Model model){
        System.out.println(username+"----"+pwd+"---"+gender+"---"+email);
        if(userMapper.queryUserByName(username)==null){
            User user=new User(0,username,pwd,gender,email,"user");
            userMapper.addUser(user);
            return "index";
        }else{
            model.addAttribute("msg","该用户已存在！！");
            return "register";
        }

    }
}
