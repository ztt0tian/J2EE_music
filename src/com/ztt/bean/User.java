package com.ztt.bean;

import javax.persistence.*;
import java.util.List;

/**
 * @author zhaotian
 * @date 2018/2/26 14:09
 */
@Entity
@Table(name = "user")
public class User {
    private String id;//使用UUID作为主键
    private String name;//昵称
    private String password;//密码
    private String type;//会员标志
    private String email;//邮箱 用于找回密码
    private Recommand_musics recommand_musics;//推荐音乐

    //一些行为记录 用于获得用户的隐式反馈数据 方便后期的个性化音乐推荐
    private List<User_search_history> search_historys;//搜索记录
    private List<User_collect_music_history> collect_music_histories;//收藏音乐记录
    private List<User_play_music_history> play_music_histories;//播放音乐记录
    private List<User_download_music_history> download_music_histories;//下载音乐记录
    @Id
    @Column(name = "id",unique = true,nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Basic
    @Column(name = "name",unique = true,nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Basic
    @Column(name="password",nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name="email",nullable = false,unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
    public List<User_search_history> getSearch_historys() {
        return search_historys;
    }
    public void setSearch_historys(List<User_search_history> search_historys) {
        this.search_historys = search_historys;
    }
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
    public List<User_collect_music_history> getCollect_music_histories() {
        return collect_music_histories;
    }

    public void setCollect_music_histories(List<User_collect_music_history> collect_music_histories) {
        this.collect_music_histories = collect_music_histories;
    }
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
    public List<User_play_music_history> getPlay_music_histories() {
        return play_music_histories;
    }

    public void setPlay_music_histories(List<User_play_music_history> play_music_histories) {
        this.play_music_histories = play_music_histories;
    }
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
    public List<User_download_music_history> getDownload_music_histories() {
        return download_music_histories;
    }

    public void setDownload_music_histories(List<User_download_music_history> download_music_histories) {
        this.download_music_histories = download_music_histories;
    }

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    public Recommand_musics getRecommand_musics() {
        return recommand_musics;
    }

    public void setRecommand_musics(Recommand_musics recommand_musics) {
        this.recommand_musics = recommand_musics;
    }
}
