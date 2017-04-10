package com.lpf.oneminute.greendao.localBean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by liupengfei on 17/3/5 12:42.
 */
@Entity
public class LocalUser {

    @Id
    private long userId;
    @Unique
    private String name;
    @NotNull
    private String passWord;

    private String address;

    private String gender;


    @Generated(hash = 173344742)
    public LocalUser() {
    }


    @Generated(hash = 318755457)
    public LocalUser(long userId, String name, @NotNull String passWord,
            String address, String gender) {
        this.userId = userId;
        this.name = name;
        this.passWord = passWord;
        this.address = address;
        this.gender = gender;
    }


    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassWord() {
        return this.passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }


    public String getGender() {
        return this.gender;
    }


    public void setGender(String gender) {
        this.gender = gender;
    }


}
