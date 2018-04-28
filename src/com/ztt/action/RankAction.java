package com.ztt.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ztt.bean.Song;
import com.ztt.bean.User;
import com.ztt.services.MusicServics;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author zhaotian
 * @date 2018/4/28 8:47
 */
@Namespace("/musicrank")
@ParentPackage("struts-default")
@Scope("prototype")
public class RankAction extends ActionSupport{
    private String rankType;

    public String getRankType() {
        return rankType;
    }

    public void setRankType(String rankType) {
        this.rankType = rankType;
    }

    @Autowired
    @Qualifier("musicService")
    private MusicServics musicServics;
    ActionContext ctx=ActionContext.getContext();
    Map<String, Object> session = ctx.getSession();//session作用域
    HttpServletRequest httpServletRequest=(HttpServletRequest)ctx.get(StrutsStatics.HTTP_REQUEST);//获取request对象
    User user=(User)session.get("login_user");//获取当前登录用户对象

    @Action(value = "song_rank",results = {
            @Result(name = "success",location ="/pages/ranklist.jsp" ),
            @Result(name = "error",location = "/error.jsp")
    })
    public String RankList(){
        System.out.println(rankType);
        ArrayList<Song> songs=new ArrayList<Song>();
        //System.out.println(rankType.equals("playTop"));
        //System.out.println(rankType=="playTop");
        if(rankType.equals("playTop")){
            songs=musicServics.get_top_play();
            httpServletRequest.setAttribute("top_play",songs);
            return "success";
        }
        else if(rankType.equals("collectTop")){
            songs=musicServics.get_top_collect();
            System.out.println(songs.size());
            httpServletRequest.setAttribute("top_collect",songs);
            return "success";
        }
        else if(rankType.equals("downloadTop")){
            songs=musicServics.get_top_download();
            System.out.println(songs.size());
            httpServletRequest.setAttribute("top_download",songs);
            return "success";
        }
        else if(rankType.equals("newTop")){
            // TODO: 2018/4/28 需要修改的调用函数 
            songs=musicServics.get_top_download();
            System.out.println(songs.size());
            httpServletRequest.setAttribute("top_new",songs);
            return "success";
        }
        else{
            httpServletRequest.setAttribute("error_message","暂未开放");
            return "error";
        }

    }

}
