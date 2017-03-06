package com.lpf.oneminute.greendao.localBean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by liupengfei on 17/3/5 18:30.
 */
@Entity
public class LocalMoney {
    @Id
    private long id;
    @NotNull
    private long userId;
    @NotNull
    private String title;
    @NotNull
    private String content;
    @NotNull
    private String time;
    @NotNull
    private String moneyUnit;
    @NotNull
    private String totalCost;

    @Generated(hash = 1796253913)
    public LocalMoney() {
    }


    @Generated(hash = 121136604)
    public LocalMoney(long id, long userId, @NotNull String title,
            @NotNull String content, @NotNull String time,
            @NotNull String moneyUnit, @NotNull String totalCost) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.time = time;
        this.moneyUnit = moneyUnit;
        this.totalCost = totalCost;
    }


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
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

    public String getMoneyUnit() {
        return this.moneyUnit;
    }

    public void setMoneyUnit(String moneyUnit) {
        this.moneyUnit = moneyUnit;
    }

    public String getTotalCost() {
        return this.totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }
}
