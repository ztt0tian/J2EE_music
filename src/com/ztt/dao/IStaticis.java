package com.ztt.dao;

import com.ztt.bean.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * @author zhaotian
 * @date 2018/4/28 15:19
 */
public interface IStaticis {
    public void add_user_music_data(User_Music_Data_Statistics user_music_data_statistics);
    public void add_user_user_similarity(Two_user_similarity two_user_similarity);
    public void add_user_recommand_music_list(Recommand_musics recommand_musics);
    public void DeleteAll();
    public User_music_score get_user_music_score(String user_id);
    public User_music_score get_select_user_music_score(String user_id);
    public ArrayList<User_music_score> get_all_user_music_score();
    public Two_user_similarity get_two_user_similarity(User_music_score user_music_score1,User_music_score user_music_score2);
    public ArrayList<Two_user_similarity> get_all_users_similarity();
    public ArrayList<String> get_similarity_order_desc(String user_id);
    public ArrayList<Song> get_one_recomand_music_top_30(String user_id);
    public LinkedHashMap<User,ArrayList<Song>> get_all_recomand_music_top_30();
}
