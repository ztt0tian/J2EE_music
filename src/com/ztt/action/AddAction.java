package com.ztt.action;

import com.opensymphony.xwork2.ActionSupport;
import com.ztt.bean.User;
import com.ztt.services.RegistImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhaotian
 * @date 2018/2/26 15:37
 */
public class AddAction extends ActionSupport {
    private User user;

    @Autowired
    private RegistImpl regist;
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String add(){
        regist.add(user);
        return SUCCESS;
    }

    public RegistImpl getRegist() {
        return regist;
    }

    public void setRegist(RegistImpl regist) {
        this.regist = regist;
    }
}
