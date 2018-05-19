package com.ztt.dao;

import com.ztt.bean.User;

import java.util.ArrayList;

/**
 * @author zhaotian
 * @date 2018/3/13 19:51
 */
public interface IUserDao {
     public void addUser(User user);
     public void delUser(User user);
     public void updateUser(User user);
     public User isExitByName(String username);
     public String isExitByEmail(String useremail);
     public User getUserByEmail(String useremail);
     public int loginUser(User user);
     public User getUserById(String id);
     public ArrayList<User> getAlluser();
}
