package com.hit.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Page {
    int num;
    int size;
    int totalCount;
    int totalPage;
    int upId;
    public Page(int num, int size, int totalCount,int upId) {
        super();
        this.num=num;
        this.size = size;
        this.totalCount = totalCount;
        this.totalPage=this.totalCount%this.size==0?this.totalCount/this.size:this.totalCount/this.size+1;
        this.upId=upId;
    }
    public String toString(){
        return totalCount+"--"+totalPage+"--"+upId;
    }
}
