package com.ztt.dao;


import com.ztt.bean.Admin;

/**
 * @author zhaotian
 * @date 2018/4/28 15:13
 */
public interface IAdminOperator {
    public Admin isExitByName(String adminname);
    public int LoginAdmin(Admin admin);
    public void init_user_musci_data();
    public void init_users_similarity();
    public void init_users_recommand_list();
    public void get_top_user();
}
