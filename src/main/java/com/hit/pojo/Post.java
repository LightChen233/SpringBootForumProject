package com.hit.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    int id;
    int like_num;
    int forum_id;
    String name;
    String user_name;
    String detail;
}
