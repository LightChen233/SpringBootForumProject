package com.hit.service;

import com.hit.mapper.ReplyMapper;
import com.hit.mapper.UserMapper;
import com.hit.pojo.Page;
import com.hit.pojo.Reply;
import com.hit.pojo.ReplyView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class ReplyServiceImpl implements ReplyService{
    @Autowired
    ReplyMapper replyMapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public void addReply(Reply reply) {
        replyMapper.addReply(reply);
    }

    @Override
    public int queryByPost(int id) {
        return replyMapper.queryByPost(id);
    }

    @Override
    public Collection<Reply> queryByPage(Page page) {
        return replyMapper.queryByPage(page);
    }

    @Override
    public ReplyView getReplyView(Page page, int postId, int currentPage) {
        Collection<Reply> replies=queryByPage(page);
        int pageNumber=page.getTotalPage();
        int[] pageNum=new int[pageNumber];
        for(int i=1;i<=pageNumber;i++){
            pageNum[i-1]=i;
        }
        return new ReplyView(replies,postId,pageNum,currentPage+1);
    }

    @Override
    public void deleteReply(int id,String name) {
//        System.out.println(id+"---"+name);
        Collection<Integer> c=replyMapper.queryByUser(name);
        for(int ic:c){
//            System.out.println(ic);
            if(ic==id){
                replyMapper.deleteReply(id);
                return;
            }
        }
    }

    @Override
    public void rootDeleteReply(int id) {
        replyMapper.deleteReply(id);
    }


}
