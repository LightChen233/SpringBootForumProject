package com.hit.service;

import com.hit.mapper.ForumMapper;
import com.hit.mapper.LikeMapper;
import com.hit.mapper.PostMapper;
import com.hit.mapper.UserMapper;
import com.hit.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    PostMapper postMapper;
    @Autowired
    ForumMapper forumMapper;
    @Autowired
    LikeMapper likeMapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public Collection<Post> queryByPage(Page page) {
        return postMapper.queryByPage(page);
    }

    @Override
    public void addPost(Post post) {
        postMapper.addPost(post);
        Forum forum=forumMapper.queryById(post.getForum_id());
        forum.setNumber(forum.getNumber()+1);
        forumMapper.updateNumber(forum);
    }

    @Override
    public String queryForumName(int id) {
        return forumMapper.queryById(id).getName();
    }

    @Override
    public PostView getPostView(Page page, int forumId, int currentPage,String username) {
        Collection<Post> posts= postMapper.queryByPage(page);
        Collection<LikePost> likePosts=new ArrayList<>();
        for (Post post:posts){
            likePosts.add(new LikePost(post,hasLike(post.getId(),userMapper.queryIdByName(username))));
        }
        String forumName=queryForumName(forumId);
        int pageNumber=page.getTotalPage();
//        System.out.println(pageNumber);
        int[] pageNum=new int[pageNumber];
        for(int i=1;i<=pageNumber;i++){
            pageNum[i-1]=i;
        }
//        System.out.println(pageNumber+"---"+currentPage);
        return new PostView(likePosts,forumId,forumName,pageNum,currentPage+1);
    }
    public boolean hasLike(int postId,int user_id){
        Collection<Integer> c=likeMapper.queryPostLike(user_id);
        for(int i:c){
            if(i==postId)
                return true;
        }
        return false;
    }

    public void transPostLike(int postId,int user_id){
        if(hasLike(postId,user_id)){
            likeMapper.deletePostLike(new Like(postId,user_id));
            postMapper.updateLikeNum(
                    new Post(postId,postMapper.queryLikeNum(postId)-1,
                            0,null,null,null));
        }else{
            likeMapper.addPostLike(new Like(postId,user_id));
            postMapper.updateLikeNum(
                    new Post(postId,postMapper.queryLikeNum(postId)+1,
                            0,null,null,null));
        }
    }

    @Override
    public void deletePost(int id,int forumId,String username) {
        if(postMapper.queryByUser(new Post(id,0,0,null,username,null))==null){
            return;
        }
        rootDeletePost(id,forumId);
    }

    @Override
    public void rootDeletePost(int id, int forumId) {
        postMapper.deletePost(id);
        Forum forum=forumMapper.queryById(forumId);
        forum.setNumber(forum.getNumber()-1);
        forumMapper.updateNumber(forum);
    }

}
