package com.hit.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
public class ReplyView {
    Collection<Reply>  replies;
    int postId;
    int[] pageNum;
    int currentPage;

}
