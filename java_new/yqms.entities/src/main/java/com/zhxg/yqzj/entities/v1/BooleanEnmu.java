package com.zhxg.yqzj.entities.v1;

public enum BooleanEnmu {

    True(1),
    False(1);
    
    private final boolean value;

    private BooleanEnmu(int val) {
        value = val==1?true:false;
    }
    
}
