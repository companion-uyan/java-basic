package person.companion.javacore.reflect.pojo;

import lombok.Data;

/**
 * 功能描述：
 *
 * @author companion
 * @date 2021/7/29 20:52
 */
@Data
public class Person {
    private String name;

    public String getIntroduce(){
        return "我的名字是：" + name;
    }
}
