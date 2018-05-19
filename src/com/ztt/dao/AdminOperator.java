package com.ztt.dao;

import com.ztt.bean.*;
import com.ztt.util.Caculate_Score;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;

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
    public Admin isExitByName(String adminname) {
        Query query=sessionFactory.getCurrentSession().createQuery("from Admin admin where admin.adminname=?").setParameter(0,adminname);
        Admin db_admin = (Admin) query.uniqueResult();
        return db_admin;
    }

    @Override
    public int LoginAdmin(Admin admin) {
        Admin db_admin=isExitByName(admin.getAdminname());
        if(db_admin==null){
            return -1;//管理员账号不存在
        }
        if (admin.getPassword().equals(db_admin.getPassword())){
            return 1;//登陆成功
        }
        return 0;//密码错误
    }
    @Override
    public void init_user_musci_data() {
        staticisDao.DeleteAll();
        ArrayList<User> users=userDao.getAlluser();
        for(User user: users){
            ArrayList<Song> songs=operationhistoryDao.GetUser_all_play_history(user.getId());
            String hql = "select count(*) from User_play_music_history c WHERE c.user=:user ";
            Query query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("user", userDao.getUserById(user.getId()));
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
    public void init_users_similarity() {
        ArrayList<Two_user_similarity> two_user_similarities=staticisDao.get_all_users_similarity();
        System.out.println(two_user_similarities.size());
        for(Two_user_similarity two_user_similarity:two_user_similarities){
            staticisDao.add_user_user_similarity(two_user_similarity);
        }
    }

    @Override
    public void init_users_recommand_list() {
        LinkedHashMap<User,ArrayList<Song>> all_recommand=staticisDao.get_all_recomand_music_top_30();
        for(User user:all_recommand.keySet()){
            Recommand_musics recommand_music=new Recommand_musics(user,all_recommand.get(user));
            staticisDao.add_user_recommand_music_list(recommand_music);
        }
    }

    @Override
    public void get_top_user() {
        staticisDao.get_similarity_order_desc("6094ea4e4d994d02ab83fcf0f62c7c87");
    }
}
