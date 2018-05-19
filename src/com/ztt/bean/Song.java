package com.ztt.bean;

import com.ztt.enumType.SongType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * @author zhaotian
 * @date 2018/4/10 11:21
 */
@Entity
@Table(name = "song",uniqueConstraints = @UniqueConstraint(columnNames = {"music_name","singer_id","album_id"}))
public class Song {
    private String song_id;
    private String song_name;
    private Singer singer;
    private Album  album;
    private String song_url;
    private String song_pic_url;
    private SongType songType;
    private String kugou_url;//酷狗上该音乐的播放url
    private long listen_nums;//总音频播放次数
    private long collect_nums;//总音频收藏次数
    private long download_nums;//总音频下载次数
    private List<User_collect_music_history> music_collect_historys;//音频收藏记录
    private List<User_download_music_history> music_download_historys;//音频下载记录
    private List<Recommand_musics> recommand_musics;//推荐列表关联
    @Id
    @GenericGenerator(name = "generator",strategy="uuid.hex")
    @Column(name= "id",unique = true,nullable = false)
    @GeneratedValue(generator="generator")
    public String getSong_id() {
        return song_id;
    }

    public void setSong_id(String song_id) {
        this.song_id = song_id;
    }
    @Column(name = "music_name")
    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "singer_id",nullable = false)
    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "album_id",nullable = true)
    public Album getAlbum() {
        return album;
    }
    public void setAlbum(Album album) {
        this.album = album;
    }
    @Column(name = "music_url",unique = true)
    public String getSong_url() {
        return song_url;
    }

    public void setSong_url(String song_url) {
        this.song_url = song_url;
    }
    @Column(name = "music_pic_url")
    public String getSong_pic_url() {
        return song_pic_url;
    }

    public void setSong_pic_url(String song_pic_url) {
        this.song_pic_url = song_pic_url;
    }
    @Column(name = "listen_counts",columnDefinition = "bigint(20) default 0")
    public long getListen_nums() {
        return listen_nums;
    }

    public void setListen_nums(long listen_nums) {
        this.listen_nums = listen_nums;
    }
    @Column(name = "music_type")
    public SongType getSongType() {
        return songType;
    }

    public void setSongType(SongType songType) {
        this.songType = songType;
    }
    @Column(name = "collect_counts",columnDefinition = "bigint(20) default 0")
    public long getCollect_nums() {
        return collect_nums;
    }

    public void setCollect_nums(long collect_nums) {
        this.collect_nums = collect_nums;
    }

    @Column(name = "download_counts",columnDefinition = "bigint(20) default 0")
    public long getDownload_nums() {
        return download_nums;
    }

    public void setDownload_nums(long download_nums) {
        this.download_nums = download_nums;
    }

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "song")
    public List<User_collect_music_history> getMusic_collect_historys() {
        return music_collect_historys;
    }

    public void setMusic_collect_historys(List<User_collect_music_history> music_collect_historys) {
        this.music_collect_historys = music_collect_historys;
    }

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "song")
    public List<User_download_music_history> getMusic_download_historys() {
        return music_download_historys;
    }

    public void setMusic_download_historys(List<User_download_music_history> music_download_historys) {
        this.music_download_historys = music_download_historys;
    }
    @Column(name = "kugou_url",unique = true)
    public String getKugou_url() {
        return kugou_url;
    }

    public void setKugou_url(String kugou_url) {
        this.kugou_url = kugou_url;
    }

    @ManyToMany
    @Cascade(value = {org.hibernate.annotations.CascadeType.ALL})
    @JoinTable(name = "recommand_song",joinColumns = {
            @JoinColumn(name = "song_id")
    },inverseJoinColumns = {@JoinColumn(name = "recommand_id")})
    public List<Recommand_musics> getRecommand_musics() {
        return recommand_musics;
    }

    public void setRecommand_musics(List<Recommand_musics> recommand_musics) {
        this.recommand_musics = recommand_musics;
    }
}
