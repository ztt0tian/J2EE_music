package util;

import java.util.UUID;

/**
 * @author zhaotian
 * @date 2018/2/26 14:25
 */
public class GenerateID {
    public static String generate(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
