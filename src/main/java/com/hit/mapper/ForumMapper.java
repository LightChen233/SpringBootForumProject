package com.hit.mapper;

import com.hit.pojo.Forum;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
@Mapper
public interface ForumMapper {
    public Collection<Forum> queryAll();
    public Forum queryById(int id);
    public Forum queryByName(String name);
    public  void deleteForum(int id);
    public  void addForum(Forum forum);
    public  void updateDecription(Forum forum);
    public void updateNumber(Forum forum);
}
