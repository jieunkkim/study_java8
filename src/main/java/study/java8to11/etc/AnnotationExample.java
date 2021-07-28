package study.java8to11.etc;


import java.lang.annotation.Annotation;
import java.util.Arrays;

@MyAnnotaion("red")
@MyAnnotaion("blue")
@MyAnnotaion("white")   // Repeatable, AnnotationContainer 를 사용하여 MyAnnotation 중복 사용 가능
public class AnnotationExample {
    public static void testAnnotation(){
      // 예제 1. MyAnnotation[] 로 출력하기
        System.out.println("예제 1. MyAnnotation[] 로 출력하기");

        MyAnnotaion[] myAnnotaions = AnnotationExample.class.getAnnotationsByType(MyAnnotaion.class);
        Arrays.stream(myAnnotaions).forEach(m -> {
            System.out.println(m.value());
        });

      // 예제 2. AnnotationContainer 로 출력하기
        System.out.println("\n예제 2. AnnotationContainer 로 출력하기");

        AnnotationContainer annotationContainer = AnnotationExample.class.getAnnotation(AnnotationContainer.class);
        Arrays.stream(annotationContainer.value()).forEach(m -> System.out.println(m.value()));
    }
}
