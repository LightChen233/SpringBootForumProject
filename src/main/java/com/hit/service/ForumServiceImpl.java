package com.hit.service;

import com.hit.mapper.ForumMapper;
import com.hit.pojo.Forum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ForumServiceImpl implements  ForumService{
        @Autowired
        ForumMapper forumMapper;

        @Override
        public Collection<Forum> queryAll() {
            return forumMapper.queryAll();
        }
}
