package person.companion.javacore.innerclass;

import lombok.NoArgsConstructor;

/**
 * 功能描述：用于匿名内部类进行继承
 *
 * @author companion
 * @date 2021/7/8 14:56
 */
@NoArgsConstructor
public class Wrapping {
    private String value;

    public Wrapping(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
