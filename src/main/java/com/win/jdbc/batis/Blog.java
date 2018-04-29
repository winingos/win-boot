package com.win.jdbc.batis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Administrator on 18/4/29.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    int id;
    String content;

    @Override
    public String toString() {
        return "id="+this.id+"   content="+this.content;
    }
}
