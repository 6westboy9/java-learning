package com.westboy.annotation;

	import java.lang.annotation.*;

/**
 * @author westboy
 * @since 2020/1/2
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface MyAnnotation {
	String name() default "zhangsan";
	String email() default "hello@example.com";
}
