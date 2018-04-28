package com.ztt.dao;

import com.ztt.bean.Song;
import com.ztt.bean.User;
import com.ztt.bean.User_search_history;
import com.ztt.util.Timetool;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * @author zhaotian
 * @date 2018/4/19 12:26
 */
@Repository("musicDao")
@Transactional
public class MusicDaoImpl implements IMusicDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public int CollectMusic(User user, String song_id) {
        return 0;
    }

    @Override
    public int CancelCollectMusic(User user, String song_id) {
        return 0;
    }

    @Override
    public int uploadMusic(User user, String song_id) {
        return 0;
    }

    @Override
    public int downloadMusic(User user, String song_id) {
        return 0;
    }

    @Override
    public ArrayList<Song> queryByMusicname(String search_key, User user) {
        // TODO: 2018/4/19 通过关键词搜索数据库中歌曲名称中含该关键词的歌曲
        ArrayList<Song> songs=new ArrayList<Song>();
        String hql="from Song song where song.song_name like :key";
        Query query=sessionFactory.getCurrentSession().createQuery(hql).setString("key", "%"+search_key+"%");
        songs=(ArrayList<Song>)query.list();
        return songs;
    }

    @Override
    public ArrayList<Song> queryByAlbumname(String search_key, User user) {
        ArrayList<Song> songs=new ArrayList<Song>();
        String hql="from Song song where song.album.album_name like :key";
        Query query=sessionFactory.getCurrentSession().createQuery(hql).setString("key","%"+search_key+"%");
        songs=(ArrayList<Song>) query.list();
        return songs;
    }

    @Override
    public ArrayList<Song> queryBySingername(String search_key, User user) {

        ArrayList<Song> songs=new ArrayList<Song>();
        String hql="from Song song where song.singer.singer_name like :key";
        Query query=sessionFactory.getCurrentSession().createQuery(hql).setString("key","%"+search_key+"%");
        songs=(ArrayList<Song>) query.list();
        return songs;
    }

    @Override
    public Song getMusicById(String music_id) {

        return (Song) sessionFactory.getCurrentSession().get(Song.class,music_id);
    }

    @Override
    public int update_music_play_counts(String music_id) {
        String hql="update Song song set song.listen_nums=song.listen_nums+1 where song.id= :song_id";
        return sessionFactory.getCurrentSession().createQuery(hql).setString("song_id",music_id).executeUpdate();
    }

    @Override
    public int update_music_collect_counts(String music_id) {
        String hql="update Song song set song.collect_nums=song.collect_nums+1 where song.id= :song_id";
        return sessionFactory.getCurrentSession().createQuery(hql).setString("song_id",music_id).executeUpdate();
    }

    @Override
    public int update_music_download_counts(String music_id) {
        String hql="update Song song set song.download_nums=song.download_nums+1 where song.id= :song_id";
        return sessionFactory.getCurrentSession().createQuery(hql).setString("song_id",music_id).executeUpdate();
    }

    @Override
    public ArrayList<Song> Play_Top() {
        String hql="from Song song order by song.listen_nums desc";
        Query query=sessionFactory.getCurrentSession().createQuery(hql).setMaxResults(30);
        ArrayList<Song> songs=(ArrayList<Song>) query.list();
        return songs;
    }

    @Override
    public ArrayList<Song> Collect_Top() {
        String hql="from Song song order by song.collect_nums desc";
        Query query=sessionFactory.getCurrentSession().createQuery(hql).setMaxResults(30);
        ArrayList<Song> songs=(ArrayList<Song>) query.list();
        return songs;
    }

    @Override
    public ArrayList<Song> Download_Top() {
        String hql="from Song song order by song.download_nums desc";
        Query query=sessionFactory.getCurrentSession().createQuery(hql).setMaxResults(30);
        ArrayList<Song> songs=(ArrayList<Song>) query.list();
        return songs;
    }
}
