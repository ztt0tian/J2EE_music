package com.ztt.dao;

import com.ztt.bean.DoubleId;
import com.ztt.bean.Song;
import com.ztt.bean.User;
import com.ztt.bean.User_Music_Data_Statistics;
import com.ztt.util.Caculate_Score;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author zhaotian
 * @date 2018/4/28 15:24
 */
@Repository("adminOperator")
@Transactional
public class AdminOperator implements IAdminOperator {
    @Autowired//通过Type自动注入
    private SessionFactory sessionFactory;
    @Resource
    private IStaticis staticisDao;
    @Resource
    private IUserDao userDao;
    @Resource
    private IUser_operation_history operationhistoryDao;
    @Override
    public void init_user_musci_data() {
        staticisDao.DeleteAll();
        ArrayList<User> users=userDao.getAlluser();
        for(User user: users){
            ArrayList<Song> songs=operationhistoryDao.GetUser_all_play_history(user.getId());
            String hql = "select count(*) from User_play_music_history c WHERE c.user=? )";
            Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, userDao.getUserById(user.getId()));
            long totals = (long) query.uniqueResult();
            for(Song song:songs){
                long one_nums=operationhistoryDao.get_music_play_counts(user.getId(),song.getSong_id());
                DoubleId doubleId=new DoubleId(user.getId(),song.getSong_id());
                Double percent=(double)one_nums/(double)totals;
                int isCollect=operationhistoryDao.isCollect(user.getId(),song.getSong_id());
                int isDownload=operationhistoryDao.isDownload(user.getId(),song.getSong_id());
                Double score= Caculate_Score.generateScore(percent,isCollect,isDownload);
                User_Music_Data_Statistics user_music_data_statistics=new User_Music_Data_Statistics(doubleId,one_nums,percent,isCollect,isDownload,score);
                staticisDao.add_user_music_data(user_music_data_statistics);
            }
        }
    }

    @Override
    public void init_user_user_similary() {

    }
}
