package study.java8to11.etc;

import java.lang.annotation.*;

/**
 * @Retention : 해당 Annotation 을 언제까지 남아있게 할 것인가 정의 (SOURCE,CLASS, RUNTIME)
 *              (ex. @Getter, @Setter 는 SOURCE 로 정의되어 있어 java 파일에만 남이있음)
 *              . 참고 URL : https://jeong-pro.tistory.com/234
 * @Target    : 이 Annotation 을 사용할 곳을 정의 (TYPE_PARAMETER, TYPE_USE 가 추가됨)
 *              . TYPE_PARAMETER
 *                 . Generic 한 Class 를 만들 때 T 와 같은 Generic Type 영역에 사용 가능
 *                   (ex. static class testMethod <@MyAnnotaion T> {}
 *                 . <M> 와 같은 Type Parameter 영역에 사용 가능. M 와 같은 Type 영역엔 사용 불가
 *                   (ex. public static <M> void print (M m) {})
 *              . TYPE_USE
 *                 . 보다 더 자유롭게 사용 가능. Type 을 사용하는 모든 곳에서 사용 가능
 *                   (ex. List<@MyAnnotation String> names = Arrays.asList("test"); )
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE_USE)
@Repeatable(AnnotationContainer.class)
public @interface MyAnnotaion {
    String value();
}
