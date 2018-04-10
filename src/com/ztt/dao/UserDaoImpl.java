package com.ztt.dao;

import com.ztt.bean.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



/**
 * @author zhaotian
 * @date 2018/3/13 19:55
 */
@Repository("userDao")
@Transactional
public class UserDaoImpl implements IUserDao {
    @Autowired//通过Type自动注入
    private SessionFactory sessionFactory;
    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }
    @Override
    public void delUser(User user) {

    }
    @Override
    public void updateUser(User user) {

    }
    @Override
    public int loginUser(User user){
        User db_user=(User)sessionFactory.getCurrentSession().load(User.class,isExitByEmail(user.getEmail()));
        if(db_user.getPassword().equals(user.getPassword())){
            System.out.println("登陆成功");
            return 1;//登录成功
        }
        return 0;//密码错误
    }
    @Override
    public boolean isExitByName(String username) {

        return false;
    }

    @Override
    public String isExitByEmail(String useremail) {
        String hql="from User user where user.email='"+useremail+"'";
        System.out.println(hql);
        Query query=sessionFactory.getCurrentSession().createQuery("from User user where user.email=?").setParameter(0,useremail);
        User db_user = (User) query.uniqueResult();
        if(db_user==null){
            return null;
        }
        return db_user.getId();
    }

    @Override
    public User getUserByEmail(String useremail) {
        if(isExitByEmail(useremail)!=null){
            String hql="from User user where user.email="+useremail;
            System.out.println(hql);
            Query query=sessionFactory.getCurrentSession().createQuery("from User user where user.email=?").setParameter(0,useremail);
            User db_user = (User) query.uniqueResult();
            return db_user;
        }
        return null;
    }
}
