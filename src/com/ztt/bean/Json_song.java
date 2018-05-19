package com.ztt.bean;

/**
 * @author zhaotian
 * @date 2018/5/4 15:09
 */
public class Json_song {
    private String song_id;
    private String song_name;
    private String song_singer;
    private String song_album_name;

    public String getSong_id() {
        return song_id;
    }

    public void setSong_id(String song_id) {
        this.song_id = song_id;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getSong_singer() {
        return song_singer;
    }

    public void setSong_singer(String song_singer) {
        this.song_singer = song_singer;
    }

    public String getSong_album_name() {
        return song_album_name;
    }

    public void setSong_album_name(String song_album_name) {
        this.song_album_name = song_album_name;
    }

    public Json_song() {
    }
    public Json_song(String song_id, String song_name, String song_singer, String song_album_name) {
        this.song_id = song_id;
        this.song_name = song_name;
        this.song_singer = song_singer;
        this.song_album_name = song_album_name;
    }
}
