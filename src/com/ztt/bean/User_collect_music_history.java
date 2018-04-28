package com.ztt.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author zhaotian
 * @date 2018/4/19 10:24
 */
@Entity
@Table(name = "collect_music_history")
public class User_collect_music_history {
    private String user_collect_id;
    private User user;
    private Song song;
    private Date music_collect_time;//收藏时间戳
    private int  collect_flag;//收藏和取消收藏标识 默认1为收藏 0为取消收藏
    @Id
    @GenericGenerator(name = "generator",strategy="uuid.hex")
    @Column(name= "id",unique = true,nullable = false)
    @GeneratedValue(generator="generator")
    public String getUser_collect_id() {
        return user_collect_id;
    }

    public void setUser_collect_id(String user_collect_id) {
        this.user_collect_id = user_collect_id;
    }
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "song_id")
    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "collect_time")
    public Date getMusic_collect_time() {
        return music_collect_time;
    }

    public void setMusic_collect_time(Date music_collect_time) {
        this.music_collect_time = music_collect_time;
    }

    @Column(name = "singal",columnDefinition="INT default 1")
    public int getCollect_flag() {
        return collect_flag;
    }

    public void setCollect_flag(int collect_flag) {
        this.collect_flag = collect_flag;
    }

    public User_collect_music_history() {
    }

    public User_collect_music_history(User user, Song song, Date music_collect_time) {
        this.user = user;
        this.song = song;
        this.music_collect_time = music_collect_time;
    }
}
