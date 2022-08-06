package person.companion.basic.exercise;

import person.companion.basic.exercise.domain.iplist.HashSetIpList;
import person.companion.basic.exercise.domain.iplist.IpList;
import org.junit.Test;

/**
 * 功能描述：
 * ip黑/白名单工具接口, 请为本interface实现一个基于内存的ip黑/白名单具体实现类
 * 要求’isInList’操作为常数级时间复杂度
 * 要求’isInList’内部操作完全基于内存，不得有网络或文件读取; 对象初始化部分如构造函数则不受此限制(如初始化时可从文件中load ip名单列表)
 * 程序设计上，请在满足上述条件的前提下，让此工具所能支持的ip列表数量尽可能大(甚至能否覆盖整个ipv4地址空间?), 内存占用尽可能小；
 * 此工具可能在多线程环境被使用
 *
 * @author companion
 * @date 2020/5/25 11:08
 */
public class IpTest {
    @Test
    public void test1() {
        IpList list = new HashSetIpList();
        System.out.println(list.isInList("192.168.1.3"));
    }
}
