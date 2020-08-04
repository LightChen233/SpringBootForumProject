package com.hit.controller;

import com.hit.mapper.UserMapper;
import com.hit.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController{
    @Autowired
    UserMapper userMapper;
    @RequestMapping("/user/userData/{username}")
    public String getUserData(@PathVariable("username")String username, Model model, HttpSession session){
        if(session.getAttribute("username").equals(username)){
            User user=userMapper.queryUserByName(username);
            model.addAttribute("userdata",user);
            return "/user/userdata";
        }
        return "/error/404";
    }
    @RequestMapping("/user/updateUserData")
    public String UpdateUserData(int userId,String username, int gender, String email,HttpSession session){
        System.out.println(userId+"---"+username+"---"+gender+"---"+email);
        if(session.getAttribute("username").equals(username)){
            User user=new User(userId,null,null,gender,email,null);
            userMapper.updateInfo(user);
            return "redirect:/user/userData/"+username;
        }
        return "/error/404";
    }
    @RequestMapping("/user/toUpdatePwd/{username}")
    public String toUpdatePwd(@PathVariable("username")String username,HttpSession session,Model model){
        if(session.getAttribute("username").equals(username)){
            User user=userMapper.queryUserByName(username);
            model.addAttribute("userdata",user);
            return "user/updatePwd";
        }
        return "/error/404";
    }
    @RequestMapping("/user/updatePwd")
    public String UpdatePwd(int userId,String username, String pwd,String newpwd){
//        System.out.println(userId+"---"+username+"---"+pwd+"---"+newpwd);
            User user=userMapper.queryUserByName(username);
            if(user.getPwd().equals(pwd)){
                user.setPwd(newpwd);
                userMapper.updatePwd(user);
                return "redirect:/user/userData/"+username;
            }

        return "/error/404";

    }
}
