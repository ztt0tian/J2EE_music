package com.ztt.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author zhaotian
 * @date 2018/4/28 13:35
 */
@Entity
@Table(name="user_music_score")
public class User_Muisc_Score {
    private  DoubleId doubleId;
    private double score;

    @Id
    public DoubleId getDoubleId() {
        return doubleId;
    }

    public void setDoubleId(DoubleId doubleId) {
        this.doubleId = doubleId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
