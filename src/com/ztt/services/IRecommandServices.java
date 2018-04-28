package com.ztt.services;

import com.ztt.bean.Song;

import java.util.ArrayList;

/**
 * @author zhaotian
 * @date 2018/4/28 14:13
 */
public interface IRecommandServices {
    public ArrayList<Song> My_recommand_songs(String user_id);
}
