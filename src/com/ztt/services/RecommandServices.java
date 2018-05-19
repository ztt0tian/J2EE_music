package com.ztt.services;

import com.ztt.bean.Song;
import com.ztt.dao.IRecommandDao;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author zhaotian
 * @date 2018/5/1 16:25
 */
public class RecommandServices implements IRecommandServices {
    @Resource
    private IRecommandDao recommandDao;
    @Override
    public ArrayList<Song> My_recommand_songs(String user_id) {

        return null;
    }
}
