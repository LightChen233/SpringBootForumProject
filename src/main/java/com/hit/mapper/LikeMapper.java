package com.hit.mapper;

import com.hit.pojo.Like;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Mapper
public interface LikeMapper {
    public Collection<Integer> queryPostLike(int user_id);
    public void addPostLike(Like like);
    public void deletePostLike(Like like);
}
