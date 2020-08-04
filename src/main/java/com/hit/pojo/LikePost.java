package com.hit.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikePost{
    int id;
    int like_num;
    int forum_id;
    String name;
    String user_name;
    String detail;
    boolean isLike;
    public LikePost(Post post,boolean isLike){
        this.id=post.getId();
        this.like_num=post.getLike_num();
        this.forum_id=post.getForum_id();
        this.name=post.getName();
        this.user_name=post.getUser_name();
        this.detail=post.getDetail();
        this.isLike=isLike;
    }
}
