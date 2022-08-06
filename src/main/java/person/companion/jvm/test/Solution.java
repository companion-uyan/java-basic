package person.companion.jvm.test;

/**
 * 功能描述：测试垃圾回收机制
 *
 * @author companion
 * @date 2020/5/25 9:39
 */
public class Solution {
    public static Solution SAVE_HOOP = null;

    public void isAlive(){
        System.out.println("yes ,i am still alive");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize executed!");
        Solution.SAVE_HOOP = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOP = new Solution();
        SAVE_HOOP = null;
        System.gc();
        Thread.sleep(500);
        if (SAVE_HOOP != null) {
            SAVE_HOOP.isAlive();
        }else {
            System.out.println("no,i am dead!");
        }

        SAVE_HOOP = null;
        System.gc();
        Thread.sleep(500);
        if (SAVE_HOOP != null) {
            SAVE_HOOP.isAlive();
        }else {
            System.out.println("no,i am dead!");
        }
    }


}
