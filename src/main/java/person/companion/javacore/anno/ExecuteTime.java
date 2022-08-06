package person.companion.javacore.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Title: 用于计算方法执行时间
 * Author companion
 * Written by: 2022/2/8 20:25
 * Describe:
 */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface ExecuteTime {
    /**
     * 以什么单位打印时间
     *
     * @return 方法执行时间
     */
    TimeUnit value();
}
