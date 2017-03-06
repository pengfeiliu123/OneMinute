package com.lpf.oneminute.greendao.localBean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by liupengfei on 17/3/5 18:46.
 */
@Entity
public class LocalMoneyDetail {

    @Id
    private long id;
    @NotNull
    private long localMoneyId;
    @NotNull
    private String content;
    @NotNull
    private int cost;
    @Generated(hash = 1123409876)
    public LocalMoneyDetail(long id, long localMoneyId, @NotNull String content,
            int cost) {
        this.id = id;
        this.localMoneyId = localMoneyId;
        this.content = content;
        this.cost = cost;
    }
    @Generated(hash = 619771023)
    public LocalMoneyDetail() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getLocalMoneyId() {
        return this.localMoneyId;
    }
    public void setLocalMoneyId(long localMoneyId) {
        this.localMoneyId = localMoneyId;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getCost() {
        return this.cost;
    }
    public void setCost(int cost) {
        this.cost = cost;
    }


}
