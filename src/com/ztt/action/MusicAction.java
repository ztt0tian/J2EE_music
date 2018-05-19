package com.ztt.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ztt.bean.*;
import com.ztt.services.MusicServics;
import com.ztt.services.UserOperateRecodeServices;
import com.ztt.util.Timetool;
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
 * @date 2018/4/19 13:26
 */
@Namespace("/music")
@ParentPackage("struts-default")
@Scope("prototype")
public class MusicAction extends ActionSupport {
    private String keyword;
    private String search_type;

    public String getSearch_type() {
        return search_type;
    }

    public void setSearch_type(String search_type) {
        this.search_type = search_type;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Autowired
    @Qualifier("musicService")
    private MusicServics musicServics;
    @Autowired
    @Qualifier("userOperateRecodeServices")
    private UserOperateRecodeServices userOperateRecodeServices;
    ActionContext ctx = ActionContext.getContext();
    Map<String, Object> session = ctx.getSession();//session作用域
    HttpServletRequest httpServletRequest = (HttpServletRequest) ctx.get(StrutsStatics.HTTP_REQUEST);//获取request对象
    User user = (User) session.get("login_user");//获取当前登录用户对象

    @Action(value = "search", results = {
            @Result(name = "success", type = "", location = "/pages/search-results.jsp"),
            @Result(name = "error", location = "/error.jsp")
    })
    public String search() {
        if (keyword == "") {
            httpServletRequest.setAttribute("error_message", "你还没输入关键词呢");
            return "error";
        }
        if (user != null) {
            //添加用户搜索记录
            System.out.println("添加记录");
            userOperateRecodeServices.add_user_search(new User_search_history(user, keyword, Timetool.getTime()));
        }
        //String searchtype=httpServletRequest.getParameter("search_type");//字符串
        System.out.println(search_type);
        System.out.println(keyword);
        ArrayList<Song> songs = musicServics.SearchMusic(user, keyword, search_type);
        //System.out.println(songs.size());
        // 获得HttpServletRequest
        httpServletRequest.setAttribute("keyword", keyword);
        httpServletRequest.setAttribute("song_songs", songs);
        return "success";
    }

    @Action(value = "play", results = {
            @Result(name = "success", location = "/pages/play.jsp"),
            @Result(name = "error", location = "/error.jsp")
    })
    public String play() {
        String music_id = httpServletRequest.getParameter("song_id");
        Song song = musicServics.GetSongById(music_id);
        if (user != null) {
            //数据库底层有触发器触发+1
            userOperateRecodeServices.add_user_play(new User_play_music_history(user, song, Timetool.getTime()));
            if (userOperateRecodeServices.is_exist_collect_record(user.getId(), music_id) == 1) {
                System.out.println("已收藏");
                httpServletRequest.setAttribute("isCollect", "yes");
            }
        }
        if(user ==null){
            //此时无用户记录并没有启动触发器手动加一
            musicServics.song_play_counts_increase(music_id);
        }
        httpServletRequest.setAttribute("song", song);
        return "success";
    }

    @Action(value = "collect", results = {
            @Result(name = "success1", location = "/pages/play.jsp"),
            @Result(name = "error", location = "/error.jsp")
    })
    public String collect() {
        String music_id = httpServletRequest.getParameter("song_id");
//        if(music_id==null){
//            System.out.println("空的");
//        }
        Song song = musicServics.GetSongById(music_id);
        System.out.println(song.getSong_name());
        if (user != null) {
            userOperateRecodeServices.add_user_collect(new User_collect_music_history(user, song, Timetool.getTime()));
            musicServics.song_collect_counts_increase(music_id);
            httpServletRequest.setAttribute("song", song);
            return "success1";
        } else {
            httpServletRequest.setAttribute("error_message", "请先登录");
            return "error";
        }
    }

    @Action(value = "mycollect", results = {
            @Result(name = "success", location = "/pages/mycollect.jsp"),
            @Result(name = "error", location = "/error.jsp")
    })
    public String mycollect() {
        if (user != null) {
            ArrayList<Song> songs = userOperateRecodeServices.get_user_colletcs(user.getId());
            httpServletRequest.setAttribute("my_collect", songs);
            return "success";
        }
        httpServletRequest.setAttribute("error_message", "请先登录");
        return "error";
    }

    @Action(value = "cancle_in_collects",results = {
            @Result(name = "success",params={"actionName","mycollect","namespace","/music","method","mycollect"}, type = "chain")
    })
    public String cancle_in_mycollect(){
        String music_id = httpServletRequest.getParameter("song_id");
        userOperateRecodeServices.delete_user_collect(user.getId(),music_id);
        musicServics.song_collect_counts_decrease(music_id);
        return "success";
    }

    @Action(value = "mylisten", results = {
            @Result(name = "success", location = "/pages/mylisten.jsp"),
            @Result(name = "error", location = "/error.jsp")
    })
    public String mylisten() {
        if (user != null) {
            ArrayList<Song> songs = userOperateRecodeServices.get_user_plays(user.getId());
            httpServletRequest.setAttribute("my_listen", songs);
            return "success";
        }
        httpServletRequest.setAttribute("error_message", "请先登录");
        return "error";
    }

    @Action(value = "recommend", results = {
            @Result(name = "success", location = "/pages/mymusic.jsp"),
            @Result(name = "error", location = "/error.jsp")
    })
    public String recommend() {
        if (user != null) {
            ArrayList<Song> songs = userOperateRecodeServices.get_user_recommands(user.getId());
            httpServletRequest.setAttribute("my_recommend", songs);
            return "success";
        }
        httpServletRequest.setAttribute("error_message", "请先登录");
        return "error";
    }

    @Action(value = "mydownload", results = {
            @Result(name = "success", location = "/pages/mydownload.jsp"),
            @Result(name = "error", location = "/error.jsp")
    })
    public String mydownload() {
        if (user != null) {
            ArrayList<Song> songs = userOperateRecodeServices.get_user_downloads(user.getId());
            httpServletRequest.setAttribute("my_download", songs);
            return "success";
        }
        httpServletRequest.setAttribute("error_message", "请先登录");
        return "error";
    }

    @Action(value = "myupload", results = {
            @Result(name = "success", location = "/pages/myupload.jsp"),
            @Result(name = "error", location = "/error.jsp")
    })
    public String upload() {
        if (user != null) {
            // TODO: 2018/4/27 需要替换的函数
            ArrayList<Song> songs = userOperateRecodeServices.get_user_plays(user.getId());
            httpServletRequest.setAttribute("my_upload", songs);
            return "success";
        }
        httpServletRequest.setAttribute("error_message", "请先登录");
        return "error";
    }

    @Action(value = "mysongsheet", results = {
            @Result(name = "success", location = "/pages/mysheet.jsp"),
            @Result(name = "error", location = "/error.jsp")
    })
    public String mysongsheet() {
        if (user != null) {
            // TODO: 2018/4/27 需要替换的函数
            ArrayList<Song> songs = userOperateRecodeServices.get_user_plays(user.getId());
            httpServletRequest.setAttribute("my_songsheet", songs);
            return "success";
        }
        httpServletRequest.setAttribute("error_message", "请先登录");
        return "error";
    }

    @Action(value = "createsongsheet", results = {
            @Result(name = "success", location = "/pages/mysheet.jsp"),
            @Result(name = "error", location = "/error.jsp")
    })
    public String createsongsheet() {
        if (user != null) {
            // TODO: 2018/4/27 需要替换的函数
            ArrayList<Song> songs = userOperateRecodeServices.get_user_plays(user.getId());
            httpServletRequest.setAttribute("my_songsheet", songs);
            return "success";
        }
        httpServletRequest.setAttribute("error_message", "请先登录");
        return "error";
    }

}
