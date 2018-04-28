package com.ztt.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author zhaotian
 * @date 2018/4/19 10:26
 */
@Entity
@Table(name = "user_search_history")
public class User_search_history {
    private String user_search_history_id;
    private User user;//搜索行为的用户
    private String search_key;//搜索关键词
    private Date search_time;//搜索时间点

    @Id
    @GenericGenerator(name = "generator",strategy="uuid.hex")
    @Column(name= "id",unique = true,nullable = false)
    @GeneratedValue(generator="generator")
    public String getUser_search_history_id() {
        return user_search_history_id;
    }

    public void setUser_search_history_id(String user_search_history_id) {
        this.user_search_history_id = user_search_history_id;
    }
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    @Column(name = "keyword")
    public String getSearch_key() {
        return search_key;
    }

    public void setSearch_key(String search_key) {
        this.search_key = search_key;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "search_time")
    public Date getSearch_time() {
        return search_time;
    }

    public void setSearch_time(Date search_time) {
        this.search_time = search_time;
    }

    public User_search_history() {
    }

    public User_search_history(User user, String search_key, Date search_time) {
        this.user = user;
        this.search_key = search_key;
        this.search_time = search_time;
    }
}
