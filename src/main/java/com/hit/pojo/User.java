package com.hit.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int userid;
    private String username;
    private String pwd;
    private int gender;
    private String email;
    private String perms;
}
