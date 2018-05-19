package com.ztt.services;

import com.ztt.bean.Admin;
import com.ztt.dao.IAdminOperator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhaotian
 * @date 2018/4/28 15:50
 */
@Service(value = "adminServices")
public class AdminServices implements IAdminService {
    @Resource
    private IAdminOperator adminOperator;

    @Override
    public int login_admin(Admin admin) {
        return adminOperator.LoginAdmin(admin);
    }

    @Override
    public void init_everyone_data() {
        adminOperator.init_user_musci_data();//初始化用户-音乐-评分数据
        adminOperator.init_users_similarity();//初始化用户-用户相似度
        adminOperator.init_users_recommand_list();//初始化用户-推荐音乐列表
    }

}
