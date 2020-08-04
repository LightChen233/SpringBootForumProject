package com.hit.controller;

import com.hit.mapper.PostMapper;
import com.hit.mapper.ReplyMapper;
import com.hit.pojo.Page;
import com.hit.pojo.Post;
import com.hit.pojo.Reply;
import com.hit.pojo.ReplyView;
import com.hit.service.PostService;
import com.hit.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
public class ReplyController {
    @Autowired
    ReplyService replyService;
    Page  PostPage;
    int size=5;
    int pageNumber;
    @RequestMapping("/user/reply/{postId}")
    public String reply(@PathVariable("postId")int postId, Model model){
        PostPage=new Page(0,size,replyService.queryByPost(postId),postId);
        ReplyView replyView=replyService.getReplyView(PostPage,postId,0);
        model.addAttribute("replyView",replyView);
        return "user/reply";
    }

    @RequestMapping("/user/reply/{postId}/p={p}")
    public String postPage(@PathVariable("postId")int postId,@PathVariable("p")int p,Model model){
        int maxsize=replyService.queryByPost(postId);
        p--;
//        System.out.println(maxsize+"---"+p);
        if(p*size<=maxsize&&p>=0){
            PostPage=new Page(p*size,size,maxsize,postId);
            ReplyView replyView=replyService.getReplyView(PostPage,postId,p);
            model.addAttribute("replyView",replyView);
        }else{
            return "redirect:/user/reply/"+postId;
        }

        return "user/reply";
    }
    @RequestMapping("/user/addReply")
    public String addPost(int postId, String name, String detail, HttpSession session, Model model){
        String username=(String)session.getAttribute("username");
        if(detail==null||detail==""){
//            model.addAttribute("replyMsg","回复内容不能为空");
        }else{
            Reply reply=new Reply(0,postId,username,detail);
            replyService.addReply(reply);
        }

        return "redirect:reply/"+postId;
    }
    @RequestMapping("/user/deleteReply/reply={replyId}/postId={postId}")
    public String deletePost(@PathVariable("replyId")int replyId,@PathVariable("postId")int postId,Model model,HttpSession session){
        String username=(String)session.getAttribute("username");
//        System.out.println(replyId+"----"+username);
        replyService.deleteReply(replyId,username);
        return "redirect:/user/reply/"+postId;
    }
    /*
    * 管理员
    * */
    @RequestMapping("/root/deleteReply/reply={replyId}/postId={postId}")
    public String rootDeletePost(@PathVariable("replyId")int replyId,@PathVariable("postId")int postId){
        replyService.rootDeleteReply(replyId);
        return "redirect:/root/reply/"+postId;
    }
    @RequestMapping("/root/reply/{postId}")
    public String rootReply(@PathVariable("postId")int postId, Model model){
        PostPage=new Page(0,size,replyService.queryByPost(postId),postId);
        ReplyView replyView=replyService.getReplyView(PostPage,postId,0);
        model.addAttribute("replyView",replyView);
        return "root/reply";
    }

    @RequestMapping("/root/reply/{postId}/p={p}")
    public String rootPostPage(@PathVariable("postId")int postId,@PathVariable("p")int p,Model model){
        int maxsize=replyService.queryByPost(postId);
        p--;
        if(p*size<=maxsize&&p>=0){
            PostPage=new Page(p*size,size,maxsize,postId);
            ReplyView replyView=replyService.getReplyView(PostPage,postId,p);
            model.addAttribute("replyView",replyView);
        }else{
            return "redirect:/root/reply/"+postId;
        }
        return "root/reply";
    }

}
