package com.qct.dao.pojo;

import javax.persistence.*;

/**
 * Created by qct on 2015/2/12.
 */
@Entity
@Table(name = "actionlog")
public class ActionLog {
    @Id
    @GeneratedValue
    private int id;
    private String uid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
