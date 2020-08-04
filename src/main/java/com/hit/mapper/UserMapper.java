package com.hit.mapper;

import com.hit.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    public User queryUserByName(String name);
    public int queryIdByName(String username);
    public void addUser(User user);
    public void updateInfo(User user);
    public void updatePwd(User user);
}
