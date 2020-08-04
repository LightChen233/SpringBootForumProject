package com.hit.mapper;

import com.hit.pojo.Page;
import com.hit.pojo.Post;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Mapper
public interface PostMapper {
    public Collection<Post> queryByPage(Page page);
    public int queryByForum(int forum_id);
    public void deletePost(int id);
    public void addPost(Post post);
    public void updateLikeNum(Post post);
    public int queryLikeNum(int id);
    public  Post queryByUser(Post post);
}
