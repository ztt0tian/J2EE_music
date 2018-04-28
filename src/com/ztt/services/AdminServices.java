package com.ztt.services;

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
    public void init_everyone_data() {
        adminOperator.init_user_musci_data();
    }
}
