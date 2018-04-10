package com.ztt.bean;

import javax.persistence.*;

/**
 * @author zhaotian
 * @date 2018/2/26 14:09
 */
@Entity
@Table(name = "user")
public class User {
    private String id;//使用UUID作为主键
    private String name;//昵称
    private String password;//密码
    private String type;//会员标志
    private String email;//邮箱 用于找回密码
    @Id
    @Column(name = "id",unique = true,nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Basic
    @Column(name = "name",unique = true,nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Basic
    @Column(name="password",nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name="email",nullable = false,unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
