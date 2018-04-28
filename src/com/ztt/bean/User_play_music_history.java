package com.ztt.bean;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author zhaotian
 * @date 2018/4/19 10:09
 */
@Entity
@Table(name = "play_music_history")
public class User_play_music_history {
    private String user_paly_history_id;
    private User user;
    private Song song;
    private Date play_begin_time;//歌曲开始播放时间点
    private Date play_end_time;//歌曲播放结束时间点，可以是自然结束，也可以是人为结束

    @Id
    @GenericGenerator(name = "generator",strategy="uuid.hex")
    @Column(name= "id",unique = true,nullable = false)
    @GeneratedValue(generator="generator")
    public String getUser_paly_history_id() {
        return user_paly_history_id;
    }

    public void setUser_paly_history_id(String user_paly_history_id) {
        this.user_paly_history_id = user_paly_history_id;
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
    @Column(name = "play_begin_time")
    public Date getPlay_begin_time() {
        return play_begin_time;
    }

    public void setPlay_begin_time(Date play_begin_time) {
        this.play_begin_time = play_begin_time;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "play_end_time")
    public Date getPlay_end_time() {
        return play_end_time;
    }

    public void setPlay_end_time(Date play_end_time) {
        this.play_end_time = play_end_time;
    }

    public User_play_music_history() {
    }

    public User_play_music_history(User user, Song song, Date play_begin_time) {
        this.user = user;
        this.song = song;
        this.play_begin_time = play_begin_time;
    }
}
