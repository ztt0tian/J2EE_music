package com.ztt.action;

import com.opensymphony.xwork2.ActionSupport;
import com.ztt.services.IAdminService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import javax.annotation.Resource;

/**
 * @author zhaotian
 * @date 2018/4/28 15:54
 */
@Namespace("/admin")
@ParentPackage("struts-default")
@Scope("prototype")
public class AdminAction extends ActionSupport{
    @Autowired
    @Qualifier("adminServices")
    private IAdminService adminServices;

    @Action(value = "init_data",results = {@Result(name = "success",location = "/init.jsp")})
    public String init_user_song_data(){
        adminServices.init_everyone_data();
        return "success";
    }
}
