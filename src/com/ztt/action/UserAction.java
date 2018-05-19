package com.ztt.action;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ztt.bean.User;
import com.ztt.services.UserServices;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import util.GenerateID;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * @author zhaotian
 * @date 2018/3/13 19:45
 */
@Namespace("/user")
@ParentPackage("struts-default")
@Scope("prototype")
public class UserAction extends ActionSupport {
    @Autowired
    @Qualifier("userServices")
    private UserServices userServices;
    private User user;

    HttpServletRequest httpServletRequest=(HttpServletRequest)ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);//获取request对象
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    @Action(value = "regist",results = {
            @Result(name = "success",location = "/index.jsp"),
            @Result(name = "error",location = "/error.jsp")})
    public String regist() throws Exception {
        user.setId(GenerateID.generate());
        int result=userServices.userRegistService(user);
        if(result==-1){
            httpServletRequest.setAttribute("error_message","邮箱已经被注册过了");
            return "error";
        }
        else if(result==0){
            httpServletRequest.setAttribute("error_message","用户名已经被人使用了");
            return "error";
        }
        else {
            httpServletRequest.setAttribute("regist_success","注册成功");
            return "success";
        }
    }
    @Action(value = "login",results = {
            @Result(name = "success",location = "/index.jsp",type = "redirect"),
            @Result(name = "pwderror",location = "/pwderror.jsp"),
            @Result(name = "noexist",location = "/noexist.jsp")
    })
    public String login() {
        int resultcode=userServices.userLoginService(user);
        if(resultcode==1){
            User db_user=userServices.getUserService(user.getEmail());
            Map<String, Object> session = ActionContext.getContext().getSession();
            session.put("login_user",db_user);
            return "success";
        }
        if(resultcode==0){
            //密码错误
            return "pwderror";
        }
        return "noexist";//用户不存在
    }
    @Action(value = "exit",results = {
            @Result(name = "success",location = "/index.jsp",type = "redirect")
    })
    public String quit(){
        Map<String, Object> session = ActionContext.getContext().getSession();
        session.remove("login_user");
        return "success";
    }
}
