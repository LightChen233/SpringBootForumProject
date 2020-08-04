package com.hit.service;

import com.hit.pojo.Page;
import com.hit.pojo.Reply;
import com.hit.pojo.ReplyView;

import java.util.Collection;

public interface ReplyService {
    public void addReply(Reply reply);
    public int queryByPost(int id);
    public Collection<Reply> queryByPage(Page page);
    public ReplyView getReplyView(Page page, int postId, int currentPage);
    public void deleteReply(int id,String name);
    public  void rootDeleteReply(int id);
}
