// package person.companion.javacore.anno.handle.processor;
//
// import person.companion.anno.processor.ExecuteTimeByProcessor;
// import lombok.extern.slf4j.Slf4j;
// import org.junit.Test;
//
// /**
//  * Title: 自定义注解处理器
//  * Author companion
//  * Written by: 2022/2/27 13:43
//  * Describe:
//  */
// @Slf4j
// public class ExecuteTimeProcessorTest {
//     @ExecuteTimeByProcessor
//     private void sleep() {
//         try {
//             Thread.sleep(1000);
//         } catch (InterruptedException e) {
//             e.printStackTrace();
//         }
//     }
//
//     @Test
//     public void test1() {
//         log.info("test");
//         sleep();
//     }
// }
