package com.hit.mapper;

import com.hit.pojo.Page;
import com.hit.pojo.Post;
import com.hit.pojo.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Mapper
public interface ReplyMapper {
    public Collection<Reply> queryByPage(Page page);
    public int queryByPost(int id);
    public void deleteReply(int id);
    public void addReply(Reply reply);
    public Collection<Integer> queryByUser(String username);
}
