package com.ztt.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author zhaotian
 * @date 2018/4/28 20:50
 */
@Entity
@Table(name = "user_similarity")
public class Two_user_similarity {
    private Double_User_Id double_user_id;
    private double similarity;

    @Id
    public Double_User_Id getDouble_user_id() {
        return double_user_id;
    }

    public void setDouble_user_id(Double_User_Id double_user_id) {
        this.double_user_id = double_user_id;
    }

    @Column(name = "similartiy")
    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

    public Two_user_similarity() {
    }

    public Two_user_similarity(Double_User_Id double_user_id, double similarity) {
        this.double_user_id = double_user_id;
        this.similarity = similarity;
    }
}
