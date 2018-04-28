package com.ztt.services;

import com.ztt.bean.*;
import com.ztt.dao.IUser_operation_history;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author zhaotian
 * @date 2018/4/22 16:22
 */
@Service("userOperateRecodeServices")
public class UserOperateRecodeServices implements IUserOperateRecordServices {
    @Resource
    private IUser_operation_history operationhistoryDao;

    @Override
    public void add_user_search(User_search_history user_search_history) {
        operationhistoryDao.add_search_history(user_search_history);
    }

    @Override
    public void add_user_collect(User_collect_music_history user_collect_music_history) {
        operationhistoryDao.add_collect_history(user_collect_music_history);
    }

    @Override
    public void add_user_play(User_play_music_history user_play_music_history) {
        operationhistoryDao.add_play_history(user_play_music_history);
    }

    @Override
    public void add_user_download(User_download_music_history user_download_music_history) {
        operationhistoryDao.add_download_history(user_download_music_history);
    }

    @Override
    public ArrayList<Song> get_user_colletcs(String userid) {
        return operationhistoryDao.GetUser_all_collect_history(userid);
    }

    @Override
    public ArrayList<Song> get_user_plays(String userid) {
        return operationhistoryDao.GetUser_all_play_history(userid);
    }

    @Override
    public ArrayList<Song> get_user_recommands(String userid) {
        return operationhistoryDao.GetUser_recommendation(userid);
    }
}
