package com.ztt.util;

/**
 * @author zhaotian
 * @date 2018/4/28 17:00
 */
public class Caculate_Score {
    public static double generateScore(double percent,int isCollect,int isDownload){
        //播放70分，收藏20分，下载10分
        return  percent*70+isCollect*20*0.2+isDownload*10*0.1;
    }
}
