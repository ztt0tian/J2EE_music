package com.ztt.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author zhaotian
 * @date 2018/4/10 9:26
 * 管理员类
 */
@Entity
@Table(name = "admin")
public class Admin {
    private String id;
    private String adminname;
    private String password;
    @Id
    @GenericGenerator(name = "generator",strategy="uuid.hex")
    @Column(name= "id",unique = true,nullable = false,length = 32)
    @GeneratedValue(generator="generator")
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    @Column(name="adminname",unique = true,nullable = false,length = 32)
    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }
    @Column(name="password",unique = false,nullable = false,length = 32)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
