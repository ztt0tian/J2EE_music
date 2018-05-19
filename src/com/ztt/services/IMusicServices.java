package com.ztt.services;

import com.ztt.bean.Song;
import com.ztt.bean.User;

import java.util.ArrayList;

/**
 * @author zhaotian
 * @date 2018/4/19 12:57
 */
public interface IMusicServices {
    public ArrayList<Song> SearchMusic(User user, String key,String searchtype);
    public Song GetSongById(String music_id);
    public int song_play_counts_increase(String music_id);
    public int song_collect_counts_increase(String music_id);
    public int song_download_counts_increase(String music_id);
    public int song_collect_counts_decrease(String music_id);
    public ArrayList<Song> get_index_new_top();
    public ArrayList<Song> get_top_play();
    public ArrayList<Song> get_top_collect();
    public ArrayList<Song> get_top_download();
}
