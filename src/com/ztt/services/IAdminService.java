package com.ztt.services;

import com.ztt.bean.Admin;

/**
 * @author zhaotian
 * @date 2018/4/28 15:49
 */
public interface IAdminService {
    public int login_admin(Admin admin);
    public void init_everyone_data();
}
