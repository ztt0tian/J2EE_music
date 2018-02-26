package test;

import util.GenerateID;

import java.util.UUID;

/**
 * @author zhaotian
 * @date 2018/2/26 13:57
 */
public class testSpring {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void hello(){
        System.out.println("hello"+getName());

    }
}
