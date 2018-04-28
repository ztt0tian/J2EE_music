package com.ztt.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author zhaotian
 * @date 2018/4/28 13:53
 */
@Embeddable
public class DoubleId  implements Serializable{
    private String user_id;
    private String music_id;

    @Column(name = "user_id")
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Column(name = "music_id")
    public String getMusic_id() {
        return music_id;
    }

    public void setMusic_id(String music_id) {
        this.music_id = music_id;
    }

    public DoubleId() {
    }

    public DoubleId(String user_id, String music_id) {
        this.user_id = user_id;
        this.music_id = music_id;
    }
}
