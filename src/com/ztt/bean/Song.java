package com.ztt.bean;

import com.ztt.enumType.SongType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author zhaotian
 * @date 2018/4/10 11:21
 */
@Entity
@Table(name = "song")
public class Song {
    private String song_id;
    private String song_name;
    private Singer singer;
    private Album  album;
    private String song_url;
    private String song_pic_url;
    private SongType songType;
    private long listen_nums;//总音频播放次数
    @Id
    @GenericGenerator(name = "generator",strategy="uuid.hex")
    @Column(name= "id",unique = true,nullable = false)
    @GeneratedValue(generator="generator")
    public String getSong_id() {
        return song_id;
    }

    public void setSong_id(String song_id) {
        this.song_id = song_id;
    }
    @Column(name = "music_name")
    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "singer_id",nullable = false)
    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "album_id",nullable = true)
    public Album getAlbum() {
        return album;
    }
    public void setAlbum(Album album) {
        this.album = album;
    }
    @Column(name = "music_url",unique = true)
    public String getSong_url() {
        return song_url;
    }

    public void setSong_url(String song_url) {
        this.song_url = song_url;
    }
    @Column(name = "music_pic_url")
    public String getSong_pic_url() {
        return song_pic_url;
    }

    public void setSong_pic_url(String song_pic_url) {
        this.song_pic_url = song_pic_url;
    }
    @Column(name = "listen_counts")
    public long getListen_nums() {
        return listen_nums;
    }

    public void setListen_nums(long listen_nums) {
        this.listen_nums = listen_nums;
    }
    @Column(name = "music_type")
    public SongType getSongType() {
        return songType;
    }

    public void setSongType(SongType songType) {
        this.songType = songType;
    }
}
