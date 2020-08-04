package com.hit.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @RequestMapping({"/","/index"})
    public String toIndex(){
//        model.addAttribute("msg","hello,Shiro");
        return "index";
    }
//    @RequestMapping("/root/dashboard")
//    public String update(){
//        return "root/dashboard";
//    }
    @RequestMapping("/root/dashboard")
    public String toDashboard(){
    return "root/dashboard";
}
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
    @RequestMapping("/login")
    public String login(String username, String password, Model model, HttpSession session){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
//        System.out.println(username+"---"+password);
        //封装用户的登录数据
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
//        token.setRememberMe(true);
        try {
            session.setAttribute("username",username);
            subject.login(token);//执行登录
            return "index";
        }catch (UnknownAccountException e){
            model.addAttribute("msg","用户名错误");
            return  "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "login";
        }

    }
    @RequestMapping("/logout")
    public  String Logout(HttpSession session){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "/index";
    }
    //未授权
    @RequestMapping("/noauth")
    public String unauthorized(){
        return "/error/noauth";
    }

}
