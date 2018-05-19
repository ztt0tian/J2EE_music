package com.ztt.dao;

import com.ztt.bean.*;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaotian
 * @date 2018/4/22 16:17
 */
@Repository("operationhistoryDao")
@Transactional
public class User_operation_historyImpl implements IUser_operation_history {
    @Autowired//通过Type自动注入
    private SessionFactory sessionFactory;
    @Resource
    private IUserDao userDao;
    @Resource
    private IMusicDao musicDao;
    @Override
    public void add_search_history(User_search_history user_search_history) {
        sessionFactory.getCurrentSession().save(user_search_history);
    }

    @Override
    public void add_collect_history(User_collect_music_history user_collect_music_history) {
        if(sessionFactory.getCurrentSession().get(User_collect_music_history.class,user_collect_music_history.getUser().getId())==null) {
            sessionFactory.getCurrentSession().save(user_collect_music_history);
        }
    }

    @Override
    public void add_play_history(User_play_music_history user_play_music_history) {
        sessionFactory.getCurrentSession().save((user_play_music_history));
    }

    @Override
    public void add_download_history(User_download_music_history user_download_music_history) {
        sessionFactory.getCurrentSession().save((user_download_music_history));
    }

    @Override
    public ArrayList<Song> GetUser_all_collect_history(String user_id) {
        ArrayList<Song> songs=new ArrayList<Song>();
        String sql="from Song song where song.id IN (SELECT c.song FROM User_collect_music_history c WHERE c.user=? order by c.music_collect_time desc)";
        Query query=sessionFactory.getCurrentSession().createQuery(sql).setParameter(0,userDao.getUserById(user_id));
        songs=(ArrayList<Song>) query.list();
        for(Song song:songs){
            System.out.println(song.getSong_name()+song.getAlbum().getAlbum_name());
        }
        return songs;
    }

    @Override
    public ArrayList<Song> GetUser_all_play_history(String user_id) {
        ArrayList<Song> songs=new ArrayList<Song>();
        String sql="from Song song where song.id IN (SELECT c.song FROM User_play_music_history c WHERE c.user=:user order by c.play_begin_time desc)";
        Query query=sessionFactory.getCurrentSession().createQuery(sql).setParameter("user",userDao.getUserById(user_id));
        songs=(ArrayList<Song>) query.list();
        for(Song song:songs){
            System.out.println(song.getSong_name()+song.getAlbum().getAlbum_name());
        }
        return songs;
    }
    @Override
    public ArrayList<Song> GetUser_recommendation(String user_id) {
        ArrayList<Song> songs=new ArrayList<Song>();
        String hql="from Recommand_musics r where r.user=:user";
        Query query=sessionFactory.getCurrentSession().createQuery(hql).setParameter("user",userDao.getUserById(user_id));
        Recommand_musics recommand_music=(Recommand_musics)query.uniqueResult();
        if(recommand_music==null){
            //新注注册用户
            return musicDao.Play_Top();
        }
        List<Song> lists=recommand_music.getRecommand_songs();
        for(Song song:lists){
            songs.add(song);
        }
        return songs;
    }
    @Override
    public ArrayList<Song> GetUser_all_download_history(String user_id) {
        ArrayList<Song> songs=new ArrayList<Song>();
        String sql="from Song song where song.id IN (SELECT c.song FROM User_download_music_history c WHERE c.user=:user order by c.music_download_time desc)";
        Query query=sessionFactory.getCurrentSession().createQuery(sql).setParameter("user",userDao.getUserById(user_id));
        songs=(ArrayList<Song>) query.list();
        for(Song song:songs){
            System.out.println(song.getSong_name()+song.getAlbum().getAlbum_name());
        }
        return songs;
    }

    @Override
    public int isCollect(String user_id, String song_id) {
        String hql="FROM User_collect_music_history c WHERE c.user=:user and c.song=:song ";
        Query query=sessionFactory.getCurrentSession().createQuery(hql).setParameter("user",userDao.getUserById(user_id)).setParameter("song",musicDao.getMusicById(song_id));
        if(query.list().size()==0){
            return 0;
        }
        else {
            return 1;
        }
    }
    @Override
    public int isDownload(String user_id, String song_id) {
        String hql="FROM User_download_music_history c WHERE c.user=:user and c.song=:song";
        Query query=sessionFactory.getCurrentSession().createQuery(hql).setParameter("user",userDao.getUserById(user_id)).setParameter("song",musicDao.getMusicById(song_id));
        if(query.list().size()==0){
            return 0;
        }
        return 1;
    }

    @Override
    public long get_music_play_counts(String user_id, String song_id) {
//        String hql = "select count(*) from User_play_music_history c WHERE c.user=? )";
//        Query query=sessionFactory.getCurrentSession().createQuery(hql).setParameter(0,userDao.getUserById(user_id));
//        int sums=(int)query.uniqueResult();
        String hql2 = "select count(*) from User_play_music_history c WHERE c.user=:user and c.song=:song";
        Query query2=sessionFactory.getCurrentSession().createQuery(hql2).setParameter("user",userDao.getUserById(user_id)).setParameter("song",musicDao.getMusicById(song_id));
        long counts=(long)query2.uniqueResult();
        return counts;
    }

    @Override
    public int delete_collect_history(String user_id, String song_id) {
        String hql = "delete from User_collect_music_history c where c.user=:user and c.song=:song  ";
        Query query =sessionFactory.getCurrentSession().createQuery(hql).setParameter("user",userDao.getUserById(user_id)).setParameter("song",musicDao.getMusicById(song_id));
        return query.executeUpdate();
    }
}
