package study.java8to11.etc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
public @interface AnnotationContainer {
    MyAnnotaion[] value();      // Container 역할의 Annotation 은 Repeatable Annotation 을 Array 로 들고 있어야 함.
}
