package com.ztt.services;

import com.ztt.bean.*;

import java.util.ArrayList;

/**
 * @author zhaotian
 * @date 2018/4/22 16:19
 */
public interface IUserOperateRecordServices {
    public void add_user_search(User_search_history user_search_history);
    public void add_user_collect(User_collect_music_history user_collect_music_history);
    public int delete_user_collect(String user_id, String song_id);
    public void add_user_play(User_play_music_history user_play_music_history);
    public void add_user_download(User_download_music_history user_download_music_history);
    public ArrayList<Song> get_user_colletcs(String userid);
    public ArrayList<Song> get_user_plays(String userid);
    public ArrayList<Song> get_user_downloads(String user_id);
    public ArrayList<Song> get_user_recommands(String userid);
    public int is_exist_collect_record(String user_id,String song_id);
}
