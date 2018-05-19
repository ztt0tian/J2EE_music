package com.ztt.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ztt.bean.Admin;
import com.ztt.dao.IAdminOperator;
import com.ztt.services.IAdminService;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author zhaotian
 * @date 2018/4/28 15:54
 */
@Namespace("/admin")
@ParentPackage("struts-default")
@Scope("prototype")
public class AdminAction extends ActionSupport{
    private Admin admin;

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Autowired
    @Qualifier("adminServices")
    private IAdminService adminServices;

    @Autowired
    @Qualifier("adminOperator")
    private IAdminOperator adminOperator;
    ActionContext ctx=ActionContext.getContext();
    Map<String, Object> session = ctx.getSession();//session作用域
    HttpServletRequest httpServletRequest=(HttpServletRequest)ctx.get(StrutsStatics.HTTP_REQUEST);//获取request对象

    @Action(value = "login",results = {
            @Result(name = "success",location = "/admin.jsp"),
            @Result(name = "pwderror",location = "/pwderror.jsp"),
            @Result(name = "noexist",location = "/noexist.jsp")
    })
    public String login_admin(){
        int login_result=adminServices.login_admin(admin);
        if (login_result==1){
            return "success";
        }
        if(login_result==0){
            //密码错误
            return "pwderror";
        }
        return "noexist";//不存在
    }
    @Action(value = "init_data",results = {@Result(name = "success",location = "/admin.jsp")})
    public String init_user_song_data(){
        adminServices.init_everyone_data();
        httpServletRequest.setAttribute("init_message","初始化成功");
        return "success";
    }

//    @Action(value = "get_top_user",results = {@Result(name = "success",location = "/init.jsp")})
//    public String get_top_user(){
//        adminOperator.get_top_user();
//        return "success";
//    }
}
