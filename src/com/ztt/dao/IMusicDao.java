package com.ztt.dao;

import com.ztt.bean.Song;
import com.ztt.bean.User;

import java.util.ArrayList;

/**
 * @author zhaotian
 * @date 2018/4/15 13:39
 */
public interface IMusicDao {
    public int CollectMusic(User user, String song_id);//收藏音乐
    public int CancelCollectMusic(User user,String song_id);//取消收藏音乐
    public int uploadMusic(User user,String song_id);//上传音乐
    public int downloadMusic(User user,String song_id);//下载音乐
    public ArrayList<Song> queryByMusicname(String search_key, User user);//按歌曲名称搜索相关歌曲
    public ArrayList<Song> queryByAlbumname(String search_key, User user);//按专辑名称搜索相关歌曲
    public ArrayList<Song> queryBySingername(String search_key, User user);//按歌手名称搜索相关歌曲
    public Song getMusicById(String music_id);//根据歌曲id获取歌曲对象
    public ArrayList<Song> Play_Top();//播放榜
    public ArrayList<Song> Collect_Top();//收藏榜
    public ArrayList<Song> Download_Top();//下载榜
    public ArrayList<Song> index_New_Top();//新歌榜
    public int update_music_play_counts(String music_id);//播放量增加
    public int update_music_collect_counts(String music_id);//收藏量增加
    public int decrease_music_collect_counts(String music_id);
    public int update_music_download_counts(String music_id);//下载量增加
}
