package com.lpf.oneminute.greendao.localBean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by liupengfei on 17/3/10 16:40.
 */
@Entity
public class LocalProtection {

    @Id
    private long id;
    @NotNull
    private String userId;
    @NotNull
    private String question1;
    @NotNull
    private String answer1;
    @NotNull
    private String question2;
    @NotNull
    private String answer2;
    @Generated(hash = 1711219641)
    public LocalProtection(long id, @NotNull String userId,
            @NotNull String question1, @NotNull String answer1,
            @NotNull String question2, @NotNull String answer2) {
        this.id = id;
        this.userId = userId;
        this.question1 = question1;
        this.answer1 = answer1;
        this.question2 = question2;
        this.answer2 = answer2;
    }
    @Generated(hash = 1604913280)
    public LocalProtection() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getQuestion1() {
        return this.question1;
    }
    public void setQuestion1(String question1) {
        this.question1 = question1;
    }
    public String getAnswer1() {
        return this.answer1;
    }
    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }
    public String getQuestion2() {
        return this.question2;
    }
    public void setQuestion2(String question2) {
        this.question2 = question2;
    }
    public String getAnswer2() {
        return this.answer2;
    }
    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }
    

   
}
