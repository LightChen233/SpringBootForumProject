package com.hit.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Forum {
    int id;
    private String name;
    private String description;
    private int number;
    public Forum(String name,String description,int number){
        this.name=name;
        this.description=description;
        this.number=number;
    }
    @Override
    public String toString(){
        return name+"---"+description;
    }
}
