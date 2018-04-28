package com.ztt.services;

import com.ztt.bean.Song;
import com.ztt.bean.User;
import com.ztt.dao.IMusicDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author zhaotian
 * @date 2018/4/19 13:16
 */
@Service("musicService")
public class MusicServics implements IMusicServices {
    @Resource
    private IMusicDao musicDao;
    @Override
    public ArrayList<Song> SearchMusic(User user, String key,String searchtype) {
        if(searchtype.equals("song")){
            return musicDao.queryByMusicname(key,user);
        }
        else if(searchtype.equals("album")){
            System.out.println(searchtype.equals("album"));
            System.out.println(key);
            return musicDao.queryByAlbumname(key,user);
        }
        else{
            return musicDao.queryBySingername(key,user);
        }
    }
    @Override
    public Song GetSongById(String music_id) {
        return musicDao.getMusicById(music_id);
    }

    @Override
    public int song_play_counts_increase(String music_id) {
        return musicDao.update_music_play_counts(music_id);
    }

    @Override
    public int song_collect_counts_increase(String music_id) {
        return musicDao.update_music_collect_counts(music_id);
    }

    @Override
    public int song_download_counts_increase(String music_id) {
        return musicDao.update_music_download_counts(music_id);
    }

    @Override
    public ArrayList<Song> get_top_play() {
        return musicDao.Play_Top();
    }

    @Override
    public ArrayList<Song> get_top_collect() {
        return musicDao.Collect_Top();
    }

    @Override
    public ArrayList<Song> get_top_download() {
        return musicDao.Download_Top();
    }
}
