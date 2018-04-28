package com.ztt.dao;

import com.ztt.bean.User_Music_Data_Statistics;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author zhaotian
 * @date 2018/4/28 15:22
 */
@Repository("staticisDao")
@Transactional
public class Staticis implements IStaticis {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void add_user_music_data(User_Music_Data_Statistics user_music_data_statistics) {
        sessionFactory.getCurrentSession().save(user_music_data_statistics);
    }

    @Override
    public void DeleteAll() {
        String sql="DELETE FROM user_music_data_statistics";
        sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
    }
}
