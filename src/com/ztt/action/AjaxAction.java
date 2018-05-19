package com.ztt.action;

/**
 * @author zhaotian
 * @date 2018/4/28 10:38
 */

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ztt.bean.Json_song;
import com.ztt.bean.Song;
import com.ztt.bean.User;
import com.ztt.bean.User_collect_music_history;
import com.ztt.services.IMusicServices;
import com.ztt.services.MusicServics;
import com.ztt.services.UserOperateRecodeServices;
import com.ztt.util.Timetool;
import freemarker.core.JSONOutputFormat;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@ParentPackage("json-default")
@Namespace("/ajax")
public class AjaxAction extends ActionSupport {
    private Map<String,Object> dataMap;
    private String collect_operation_result;
    private ArrayList<Song> songs;
    public ArrayList<Song> getSongs() {
        return songs;
    }

    public String getCollect_operation_result() {
        return collect_operation_result;
    }

    public void setCollect_operation_result(String collect_operation_result) {
        this.collect_operation_result = collect_operation_result;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }
    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }


    @Autowired
    @Qualifier("musicService")
    private MusicServics musicServics;
    @Autowired
    @Qualifier("userOperateRecodeServices")
    private UserOperateRecodeServices userOperateRecodeServices;
    public MusicServics getMusicServics() {
        return musicServics;
    }

    public void setMusicServics(MusicServics musicServics) {
        this.musicServics = musicServics;
    }

    ActionContext ctx=ActionContext.getContext();
    Map<String, Object> session = ctx.getSession();//session作用域
    HttpServletRequest httpServletRequest=(HttpServletRequest)ctx.get(StrutsStatics.HTTP_REQUEST);//获取request对象
    User user=(User)session.get("login_user");//获取当前登录用户对象
    @SkipValidation
    @Action(value = "index",results = {
            @Result(type = "json",params = {"root","dataMap"})
    })
    public String ajaxIndex(){
        this.dataMap=new HashMap<String, Object>();
       // System.out.println(musicServics.get_index_new_top().size());
        ArrayList<Json_song> json_songs=new ArrayList<Json_song>();//首頁新歌榜
        ArrayList<Json_song> json_top_songs=new ArrayList<Json_song>();//热门歌曲
        songs=musicServics.get_index_new_top();
        ArrayList<Song> top_songs=musicServics.get_top_play();
        int top_nums=0;//限制首页显示的歌曲数目
        for(Song song:songs){
            json_songs.add(new Json_song(song.getSong_id(),song.getSong_name(),song.getSinger().getSinger_name(),song.getAlbum().getAlbum_name()));
        }
        for (Song song:top_songs){
            if(top_nums>=6)break;
            json_top_songs.add(new Json_song(song.getSong_id(),song.getSong_name(),song.getSinger().getSinger_name(),song.getAlbum().getAlbum_name()));
            top_nums=top_nums+1;
        }
        JSONArray json_format_songs=new JSONArray();//新歌的
        JSONArray json_index_top_songs=new JSONArray();//热歌的
        for(Json_song song:json_songs) {
            JSONObject jsonObject = JSONObject.fromObject(song);
            json_format_songs.add(jsonObject);
        }
        for(Json_song song:json_top_songs) {
            JSONObject jsonObject = JSONObject.fromObject(song);
            json_index_top_songs.add(jsonObject);
        }
        dataMap.put("result","成功");
        dataMap.put("songs",json_format_songs.toString());
        dataMap.put("top_songs",json_index_top_songs.toString());
        return SUCCESS;
    }
    @Action(value = "confirm_collect",results = {
            @Result(type = "json",params = {"root","collect_operation_result"})
    })
    public String confirm_Collect(){
        HttpServletRequest request= ServletActionContext.getRequest();
        String user_id=request.getParameter("user_id");
        String song_id=request.getParameter("song_id");
        System.out.println(user_id+"----"+song_id);
        collect_operation_result="收藏成功";
        userOperateRecodeServices.add_user_collect(new User_collect_music_history(user, musicServics.GetSongById(song_id), Timetool.getTime()));
        musicServics.song_collect_counts_increase(song_id);
        return SUCCESS;
    }
    @Action(value = "cancle_collect",results = {
            @Result(type = "json",params = {"root","collect_operation_result"})
    })
    public String cancle_Collect(){
        HttpServletRequest request= ServletActionContext.getRequest();
        String user_id=request.getParameter("user_id");
        String song_id=request.getParameter("song_id");
        System.out.println(user_id+"----"+song_id);
        collect_operation_result="取消收藏成功";
        userOperateRecodeServices.delete_user_collect(user_id,song_id);
        musicServics.song_collect_counts_decrease(song_id);
        return SUCCESS;
    }
}
