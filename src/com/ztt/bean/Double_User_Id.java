package com.ztt.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author zhaotian
 * @date 2018/4/30 14:17
 */
@Embeddable
public class Double_User_Id implements Serializable {
    private String user_1_id;
    private String user_2_id;

    @Column(name = "user_1_id")
    public String getUser_1_id() {
        return user_1_id;
    }

    public void setUser_1_id(String user_1_id) {
        this.user_1_id = user_1_id;
    }

    @Column(name = "user_2_id")
    public String getUser_2_id() {
        return user_2_id;
    }

    public void setUser_2_id(String user_2_id) {
        this.user_2_id = user_2_id;
    }

    public Double_User_Id() {
    }

    public Double_User_Id(String user_1_id, String user_2_id) {
        this.user_1_id = user_1_id;
        this.user_2_id = user_2_id;
    }
}
