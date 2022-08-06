package person.companion.basic;

import person.companion.basic.domain.PropertyDomain;
import org.junit.Test;

/**
 * 功能描述：
 *
 * @author companion
 * @date 2020/6/2 10:27
 */
public class PropertyTest {
    @Test
    public void test1() {
        PropertyDomain domain = new PropertyDomain();
        domain.setAge(15);
        domain.setRole("student");
        System.out.println(Float.MAX_VALUE);
        System.out.println(Double.MAX_VALUE);
    }
}
