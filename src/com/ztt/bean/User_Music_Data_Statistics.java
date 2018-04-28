package com.ztt.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author zhaotian
 * @date 2018/4/28 14:00
 */
@Entity
@Table(name="user_music_data_statistics")
public class User_Music_Data_Statistics {
    private DoubleId doubleId;
    private long play_counts;//播放次数
    private double play_counts_percent;//播放次数所占百分比
    private int isCollec;//收藏标志
    private int download;//下载标志
    private double score;//评分

    @Id
    public DoubleId getDoubleId() {
        return doubleId;
    }

    public void setDoubleId(DoubleId doubleId) {
        this.doubleId = doubleId;
    }

    @Column(name = "play_conuts")
    public long getPlay_counts() {
        return play_counts;
    }

    public void setPlay_counts(long play_counts) {
        this.play_counts = play_counts;
    }
    @Column(name = "percent")
    public double getPlay_counts_percent() {
        return play_counts_percent;
    }

    public void setPlay_counts_percent(double play_counts_percent) {
        this.play_counts_percent = play_counts_percent;
    }

    @Column(name = "isCollect")
    public int getIsCollec() {
        return isCollec;
    }

    public void setIsCollec(int isCollec) {
        this.isCollec = isCollec;
    }

    @Column(name = "isDownload")
    public int getDownload() {
        return download;
    }

    public void setDownload(int download) {
        this.download = download;
    }
    @Column(name = "score")
    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public User_Music_Data_Statistics() {
    }

    public User_Music_Data_Statistics(DoubleId doubleId, long play_counts, double play_counts_percent, int isCollec, int download,double score) {
        this.doubleId = doubleId;
        this.play_counts = play_counts;
        this.play_counts_percent = play_counts_percent;
        this.isCollec = isCollec;
        this.download = download;
        this.score=score;
    }
}
