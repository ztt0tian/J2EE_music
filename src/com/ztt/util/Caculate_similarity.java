package com.ztt.util;


/**
 * @author zhaotian
 * @date 2018/4/30 13:42
 */
public class Caculate_similarity {
    public static double cos(double x1,double x2){//一首歌的两个评分之间的相似度
        Double cos=(x1*x1+x1*x2)/(Math.sqrt(x1*x1+x1*x1)*Math.sqrt(x1*x1+x2*x2));//以第一个数据的评分数值作为固定的横坐标，避免因评分数值本身大小而影响相似度计算
        return cos;
    }
}
