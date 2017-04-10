package com.lpf.oneminute.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import cn.bmob.v3.BmobUser;

/**
 * Created by liupengfei on 17/2/24 16:52.
 */
public class User extends BmobUser{

    private String name;

    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
