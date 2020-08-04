package com.hit.service;

import com.hit.pojo.Page;
import com.hit.pojo.Post;
import com.hit.pojo.PostView;

import java.util.Collection;

public interface PostService {
    public Collection<Post> queryByPage(Page page);
    public void addPost(Post post);
    public String queryForumName(int id);
    public PostView getPostView(Page page, int forumId, int currentPage,String username);
    public boolean hasLike(int postId,int user_id);
    public void transPostLike(int postId,int user_id);
    public void deletePost(int id,int forumId,String username);
    public void rootDeletePost(int id,int forumId);
}
