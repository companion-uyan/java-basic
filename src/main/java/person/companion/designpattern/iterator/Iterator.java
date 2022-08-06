package person.companion.designpattern.iterator;

/**
 * 功能描述：
 *
 * @author companion
 * @date 2021/7/8 10:10
 */
public interface Iterator {
    /**
     * @return 是否已经遍历完成
     */
    boolean end();

    /**
     * @return 找到下一个节点
     */
    Object next();

    /**
     * @return 返回当前节点
     */
    Object current();
}
