package com.ztt.services;

import com.ztt.bean.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author zhaotian
 * @date 2018/2/26 15:00
 */
public class RegistImpl implements Regist {

    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void add(User user) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        session.save(user);
        transaction.commit();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
