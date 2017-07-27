package com.jiang.douban.entity.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by jiang on 2017/3/24.
 */
@Entity
public class SearchHistory {
    @Id(autoincrement = true)
    private Long id;
    @Unique
    private String text;

    @Generated(hash = 865810149)
    public SearchHistory(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public SearchHistory(String text) {
        this.text = text;
    }

    @Generated(hash = 1905904755)
    public SearchHistory() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
