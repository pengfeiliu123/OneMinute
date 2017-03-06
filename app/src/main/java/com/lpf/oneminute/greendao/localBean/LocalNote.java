package com.lpf.oneminute.greendao.localBean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by liupengfei on 17/3/5 17:45.
 */
@Entity
public class LocalNote {

    @Id
    private long id;

    private String userId;
    @NotNull
    private String title;
    @NotNull
    private String content;
    @NotNull
    private String time;
    @Generated(hash = 1197265134)
    public LocalNote(long id, String userId, @NotNull String title,
            @NotNull String content, @NotNull String time) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.time = time;
    }
    @Generated(hash = 1860700554)
    public LocalNote() {
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
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
}
