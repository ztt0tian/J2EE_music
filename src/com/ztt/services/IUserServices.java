package com.ztt.services;

import com.ztt.bean.User;

import java.util.ArrayList;

/**
 * @author zhaotian
 * @date 2018/2/26 14:59
 */
public interface IUserServices {
    public int userRegistService(User user);
    public ArrayList<User> getAllUserService();
    public User getUserService(String useremail);
    public User getUserByID(String userid);
    public User getUserByName(String username);
    public int userLoginService(User user);
    public int userModifyService(User user);
}
