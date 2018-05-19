package com.ztt.bean;


import java.util.LinkedHashMap;


/**
 * @author zhaotian
 * @date 2018/4/30 12:51
 */
public class User_music_score {
    private String user_id;
    private LinkedHashMap<String,Double> music_score;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public LinkedHashMap<String, Double> getMusic_score() {
        return music_score;
    }

    public void setMusic_score(LinkedHashMap<String, Double> music_score) {
        this.music_score = music_score;
    }

    public User_music_score(String user_id, LinkedHashMap<String, Double> music_score) {
        this.user_id = user_id;
        this.music_score = music_score;
    }

    public User_music_score() {
    }
}
