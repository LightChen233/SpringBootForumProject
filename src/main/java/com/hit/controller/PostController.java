package com.hit.controller;

import com.hit.mapper.PostMapper;

import com.hit.mapper.UserMapper;
import com.hit.pojo.Page;
import com.hit.pojo.Post;
import com.hit.pojo.User;
import com.hit.service.PostService;
import com.hit.pojo.PostView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    PostMapper postMapper;
    @Autowired
    PostService postService;
    @Autowired
    UserMapper userMapper;
    Page  ForumPage;
    int size=5;
    int pageNumber;
    /*
     *  用户
     * */
    @RequestMapping("/user/post/{forumId}")
    public String post(@PathVariable("forumId")int forumId, Model model,HttpSession session){
//        System.out.println(postMapper.queryByForum(forumId));
        ForumPage=new Page(0,size,postMapper.queryByForum(forumId),forumId);
        String username= (String) session.getAttribute("username");
//        model.addAttribute("msg",session.getAttribute("postMsg"));
        model.addAttribute("postView",postService.getPostView(ForumPage,forumId,0,username));
        return "user/post";
    }
    @RequestMapping("/user/post/{forumId}/p={p}")
    public String postPage(@PathVariable("forumId")int forumId,@PathVariable("p")int p ,Model model,HttpSession session){
        int maxsize=postMapper.queryByForum(forumId);
        String username= (String) session.getAttribute("username");
        p--;
        if(p*size<=maxsize&&p>=0){
            ForumPage=new Page(p*size,size,maxsize,forumId);
            model.addAttribute("postView",postService.getPostView(ForumPage,forumId,p,username));
        }else{
            return "redirect:/user/post/"+forumId;
        }

        return "user/post";
    }
    @RequestMapping("/user/addPost")
    public String addPost(int forumId, String name,String detail,HttpSession session,  Model model){
        String username=(String)session.getAttribute("username");
//        System.out.println(detail);
//        System.out.println(name+"----"+detail);
        if((name==null||name=="")&&(detail==null||detail=="")){
//            session.setAttribute("postMsg","标题和内容不能为空");
        }else if(name==null||name==""){
//            session.setAttribute("postMsg","标题不能为空");
        }else if(detail==null||detail==""){
//            session.setAttribute("postMsg","内容不能为空");
        }else{
            Post post=new Post(0,0,forumId,name,username,detail);
            postService.addPost(post);
        }
        return "redirect:post/"+forumId;
    }
    @RequestMapping("/user/deletePost/post={postId}/forum={forumId}")
    public String deletePost(@PathVariable("postId")int postId,@PathVariable("forumId")int forumId,HttpSession session){
        String username=(String)session.getAttribute("username");
        postService.deletePost(postId,forumId,username);
        return "redirect:/user/post/"+forumId;
    }
    //AJax
    @RequestMapping(value = "/user/post/like/{postId}", method = RequestMethod.POST)
    @ResponseBody
    public String data(@PathVariable("postId")String postID,HttpSession session) {
        int postId=Integer.parseInt(postID);
        String username=(String)session.getAttribute("username");
//        System.out.println(userMapper.queryIdByName(username));
        postService.transPostLike(postId,userMapper.queryIdByName(username));
        return "ok";
    }

    /*
    *   管理员
    * */
    @RequestMapping("/root/post/{forumId}")
    public String rootPost(@PathVariable("forumId")int forumId, Model model,HttpSession session){
//        System.out.println(postMapper.queryByForum(forumId));
        ForumPage=new Page(0,size,postMapper.queryByForum(forumId),forumId);
        String username= (String) session.getAttribute("username");
//        model.addAttribute("msg",session.getAttribute("postMsg"));
        model.addAttribute("postView",postService.getPostView(ForumPage,forumId,0,username));
        return "root/post";
    }
    @RequestMapping("/root/deletePost/post={postId}/forum={forumId}")
    public String rootDeletePost(@PathVariable("postId")int postId,@PathVariable("forumId")int forumId){
        postService.rootDeletePost(postId,forumId);
        return "redirect:/root/post/"+forumId;
    }
    @RequestMapping("/root/post/{forumId}/p={p}")
    public String rootPostPage(@PathVariable("forumId")int forumId,@PathVariable("p")int p ,Model model,HttpSession session){
        int maxsize=postMapper.queryByForum(forumId);
        String username= (String) session.getAttribute("username");
        p--;
        if(p*size<=maxsize&&p>=0){
            ForumPage=new Page(p*size,size,maxsize,forumId);
            model.addAttribute("postView",postService.getPostView(ForumPage,forumId,p,username));
        }else{
            return "redirect:/root/post/"+forumId;
        }

        return "root/post";
    }
}
