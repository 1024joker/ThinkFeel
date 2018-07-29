package com.mxthd.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 邮件实体类
 */
public class Mail implements Serializable {
    private Integer id;
    private Integer uid;
    private String title;
    private String context;
    private Date createTime;
    private String mail;

    @Override
    public String toString() {
        return "Mail{" +
                "id=" + id +
                ", uid=" + uid +
                ", title='" + title + '\'' +
                ", context='" + context + '\'' +
                ", createTime=" + createTime +
                ", mail='" + mail + '\'' +
                '}';
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Mail() {
    }

    public Mail(Integer uid, String title, String context, String mail) {
        this.uid = uid;
        this.title = title;
        this.context = context;
        this.mail = mail;
    }

    public Mail(String mail) {
        this.mail = mail;
    }
}
