package com.ztt.dao;

import com.ztt.bean.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author zhaotian
 * @date 2018/4/30 17:30
 */
@Repository("recommandDao")
public class RecommandDaoImpl implements IRecommandDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Resource
    private IUserDao userDao;
    @Override
    public ArrayList<User> get_similary_users(String user_id) {
        //from User_Music_Data_Statistics u where u.doubleId.user_id=:user_id
        String hql="from Two_user_similarity t where t.double_user_id.user_1_id=:user1 or t.double_user_id.user_2_id=:user2 order by desc";
       Query query= sessionFactory.getCurrentSession().createQuery(hql).setParameter("user1",user_id).setParameter("user2",user_id);
       ArrayList<User> users=new ArrayList<User>();
       ArrayList<String> list=(ArrayList<String>) query.list();
       for(String temp:list){
           users.add(userDao.getUserById(temp));
       }
       for (User user:users){
           System.out.println(user.getName());
       }
       return users;
    }
}
