package com.ztt.dao;

import com.ztt.bean.User;

import java.util.ArrayList;

/**
 * @author zhaotian
 * @date 2018/4/30 17:26
 */
public interface IRecommandDao {
    public ArrayList<User> get_similary_users(String user_id);
}
