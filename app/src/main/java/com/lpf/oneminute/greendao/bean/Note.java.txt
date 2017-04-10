package com.lpf.oneminute.greendao.bean;

import org.json.JSONObject;

import cn.bmob.v3.BmobObject;

/**
 * Created by liupengfei on 17/2/25 11:05.
 * record note
 */

public class Note extends BmobObject {


    private String userId;
    private String title;
    private String content;
    private String time;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
