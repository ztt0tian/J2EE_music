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
    private String name;
    private String password;
    private int type;
    private String email;
    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
