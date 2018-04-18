package com.ztt.dao;

import com.ztt.bean.Song;

import java.util.ArrayList;

/**
 * @author zhaotian
 * @date 2018/4/15 13:39
 */
public interface IMusicDao {
    public ArrayList<Song> queryByMusicname(String search_key);//按歌曲名称搜索歌曲
    public int CollectMusic(String user_id,String song_id);//收藏音乐
    public int CancelCollectMusic(String user_id,String song_id);//取消收藏音乐
    public int uploadMusic(String user_id,Song song);//上传音乐
}
