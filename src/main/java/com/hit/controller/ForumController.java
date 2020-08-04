package com.hit.controller;

import com.hit.mapper.ForumMapper;
import com.hit.pojo.Forum;
import com.hit.service.ForumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;

@Controller
public class ForumController {
    @Autowired
    ForumMapper forumMapper;
    /*
    *   用户
    */
    @RequestMapping("/user/forum")
    public String forum(Model model){
        Collection<Forum> forums=forumMapper.queryAll();
        System.out.println(forums);
        model.addAttribute("forums",forums);
        return "user/forum";
    }
    /*
    *   管理员
    */
    @RequestMapping("/root/forum")
    public String forumRoot(Model model){
        Collection<Forum> forums=forumMapper.queryAll();
        System.out.println(forums);
        model.addAttribute("forums",forums);
        return "root/dashboard";
    }
    @RequestMapping("/root/delete/{id}")
    public String deleteForum(@PathVariable("id")int id){
        forumMapper.deleteForum(id);
        return "redirect:/root/forum";
    }
    @RequestMapping("/root/toUpdate/{id}")
    public String toUpdate(@PathVariable("id")int id,Model model){
        Forum forum=forumMapper.queryById(id);
        model.addAttribute("forum",forum);
        return "root/updateForum";
    }
    @RequestMapping("/root/updateForum")
    public String updateForum(String name,String description,int number,Model model){
        forumMapper.updateDecription(new Forum(name,description,number));
        return "redirect:forum";
    }
    @RequestMapping("/root/addForum")
    public String addForum(String name,String description,Model model){
        if(name==null||name==""){
            model.addAttribute("msg","名称不能为空");
            return "root/addForum";
        }else if(description==null||description==""){
            model.addAttribute("msg","描述不能为空");
            return "root/addForum";
        }else{

            if(forumMapper.queryByName(name)==null){
                Forum forum1=new Forum(name,description,0);
                forumMapper.addForum(forum1);
                return "redirect:forum";
            }else{
                model.addAttribute("msg","该板块已创建");
                return "root/addForum";
            }


        }
    }
    @RequestMapping("/root/toAdd")
    public String toAdd(){
        return "root/addForum";
    }
}
