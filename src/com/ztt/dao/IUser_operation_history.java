package com.ztt.dao;

import com.ztt.bean.*;

import java.util.ArrayList;

/**
 * @author zhaotian
 * @date 2018/4/22 16:15
 */
public interface IUser_operation_history {
    public void add_search_history(User_search_history user_search_history);
    public void add_collect_history(User_collect_music_history user_collect_music_history);
    public int delete_collect_history(String user_id, String song_id);
    public void add_play_history(User_play_music_history user_play_music_history);
    public void add_download_history(User_download_music_history user_download_music_history);
    public ArrayList<Song> GetUser_all_collect_history(String user_id);
    public ArrayList<Song> GetUser_all_play_history(String user_id);
    public ArrayList<Song> GetUser_all_download_history(String user_id);
    public ArrayList<Song> GetUser_recommendation(String user_id);
    public int isCollect(String user_id,String song_id);
    public int isDownload(String user_id,String song_id);
    public long get_music_play_counts(String user_id,String song_id);
}
