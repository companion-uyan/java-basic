package person.companion.javacore.reflect.pojo;

import lombok.Data;

/**
 * 功能描述：
 *
 * @author companion
 * @date 2021/7/29 20:53
 */
@Data
public class Student extends Person {
    private String homeWork;

    // 定义一个私有方法
    private void testPrivate(String name){
        this.setName(name);
    }

    public String getHomeWork(){
        return "我的家庭作业是：" + homeWork;
    }
}
