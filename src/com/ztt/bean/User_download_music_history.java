package com.ztt.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author zhaotian
 * @date 2018/4/22 15:34
 */
@Entity
@Table(name = "download_music_history")
public class User_download_music_history {
    private String user_download_id;
    private User user;
    private Song song;
    private Date music_download_time;//下载时间戳

    @Id
    @GenericGenerator(name = "generator",strategy="uuid.hex")
    @Column(name= "id",unique = true,nullable = false)
    @GeneratedValue(generator="generator")
    public String getUser_download_id() {
        return user_download_id;
    }

    public void setUser_download_id(String user_download_id) {
        this.user_download_id = user_download_id;
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
    @Column(name = "download_time")
    public Date getMusic_download_time() {
        return music_download_time;
    }

    public void setMusic_download_time(Date music_download_time) {
        this.music_download_time = music_download_time;
    }
}
