package com.ztt.util;

/**
 * @author zhaotian
 * @date 2018/4/28 17:00
 */
public class Caculate_Score {
    public static double generateScore(double percent,int isCollect,int isDownload){
        //播放7分，收藏2分，下载1分
        //return  percent*7+isCollect*2*0.2+isDownload*1*0.1;
        return  percent*7.0+isCollect*2.0+isDownload*1.0;
    }
}
