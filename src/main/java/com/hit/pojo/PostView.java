package com.hit.pojo;

import com.hit.mapper.PostMapper;
import com.hit.pojo.Page;
import com.hit.pojo.Post;
import com.hit.service.PostService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Data
@AllArgsConstructor
public class PostView {
    Collection<LikePost> posts;
    int forumId;
    String forumName;
    int[] pageNum;
    int currentPage;
}
