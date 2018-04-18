package com.ztt.dao;

import com.ztt.bean.Album;

import java.util.ArrayList;

/**
 * @author zhaotian
 * @date 2018/4/15 14:19
 */
public interface IAlbumDao {
    public ArrayList<Album> queryAlbumByalbumname(String search_key);//根据专辑名搜索相关专辑
    public int CollectAlbum(String user_id,String album_id);//收藏专辑
    public int CancelCollectAlbum(String user_id,String album_id);//取消收藏专辑
    public int addAlbum(String user_id,Album album);//添加专辑
}
