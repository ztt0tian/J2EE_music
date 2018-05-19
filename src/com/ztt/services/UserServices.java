package com.ztt.services;

import com.ztt.bean.User;
import com.ztt.dao.IUserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author zhaotian
 * @date 2018/3/13 20:00
 */
@Service(value = "userServices")
public class UserServices implements IUserServices {
    @Resource
    private IUserDao userDao;
    @Override
    public int userRegistService(User user) {
        if(userDao.isExitByEmail(user.getEmail())!=null){
            return -1;//邮箱已经被注册过了
        }
        else if(userDao.isExitByName(user.getName())!=null){
            return 0;//用户名已经有人使用
        }
        else {
            userDao.addUser(user);
            return 1;
        }
    }
    @Override
    public int userLoginService(User user) {
        if(userDao.isExitByEmail(user.getEmail())!=null){
            return userDao.loginUser(user);
        }
        return -1;//不存在该用户
    }
    @Override
    public int userModifyService(User user) {
        return 0;
    }

    @Override
    public ArrayList<User> getAllUserService() {
        return null;
    }

    @Override
    public User getUserService(String useremail) {
        return userDao.getUserByEmail(useremail);
    }

    @Override
    public User getUserByID(String userid) {
        return userDao.getUserById(userid);
    }

    @Override
    public User getUserByName(String username) {
        return userDao.isExitByName(username);
    }
}
