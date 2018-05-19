package com.ztt.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ztt.bean.Song;
import com.ztt.bean.User;
import com.ztt.bean.User_download_music_history;
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

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * @author zhaotian
 * @date 2018/4/27 18:48
 */
@Namespace("/file")
@ParentPackage("struts-default")
public class FileAction extends ActionSupport {
    private String music_url;
    private String filename;
    private InputStream inputStream;
    @Autowired
    @Qualifier("musicService")
    private MusicServics musicServics;
    @Autowired
    @Qualifier("userOperateRecodeServices")
    private UserOperateRecodeServices userOperateRecodeServices;
    ActionContext ctx=ActionContext.getContext();
    Map<String, Object> session = ctx.getSession();//session作用域
    HttpServletRequest httpServletRequest=(HttpServletRequest)ctx.get(StrutsStatics.HTTP_REQUEST);//获取request对象
    User user=(User)session.get("login_user");//获取当前登录用户对象
    public String getMusic_url() {
        return music_url;
    }

    public void setMusic_url(String music_url) {
        this.music_url = music_url;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public InputStream getInputStream() throws IOException {
        InputStream inputStream=null;
        int HttpResultCode;//服务器返回的状态
        URL url=new URL(music_url);
        URLConnection urlConnection=url.openConnection();// 试图连接并取得返回状态码
        urlConnection.connect();
        HttpURLConnection httpURLConnection=(HttpURLConnection) urlConnection;
        HttpResultCode=httpURLConnection.getResponseCode();
        System.out.println(HttpResultCode);
        if(HttpResultCode!=HttpURLConnection.HTTP_OK){
            System.out.println("无法连接");
            return null;
        }
        else{
            try {
                inputStream = httpURLConnection.getInputStream();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
    @Action(value = "download",results = {
            @Result(name = "success", type = "stream", params = { "contentType",
                    "application/octet-stream;charset=UTF-8", "inputName",
                    "inputStream", "contentDisposition",
                    "attachment;filename=\"${filename}\"", "bufferSize", "10240" }),
            @Result(name = "error",location = "/error.jsp")
    })
    public  String download() throws UnsupportedEncodingException {
        String music_id=httpServletRequest.getParameter("song_id");
        Song download_music=musicServics.GetSongById(music_id);
        music_url=download_music.getSong_url();
        String name_string=download_music.getSinger().getSinger_name()+"-"+download_music.getSong_name()+".mp3";
        System.out.println(name_string);
        filename=new String(name_string.getBytes("utf-8"),"ISO_8859_1");
        try {
            inputStream=getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(user!=null){
            userOperateRecodeServices.add_user_download(new User_download_music_history(user,download_music, Timetool.getTime()));
        }
        musicServics.song_download_counts_increase(music_id);//无论登陆与否下载次数均加1
        if(inputStream==null){
            httpServletRequest.setAttribute("error_message","抱歉，该资源暂时还无法下载");
            return "error";
        }
        return "success";
    }
}
