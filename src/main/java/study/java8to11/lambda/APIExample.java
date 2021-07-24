package study.java8to11.lambda;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;

public class APIExample {
    void testAPI(){
        List<String> color = new ArrayList<>();
        color.add("white");
        color.add("red");
        color.add("blue");
        color.add("black");


        /**
         * Interface의 변화
         * (AS-IS) Interface 가 static 외엔 구현된 메소드를 가질 수 없어서
         *         Interface 를 참조한 abstract Class 를 생성
         *         다른 Class 들은 위 Class 를 extends 하여 필요한 메소드만 override 후 사용
         * (TO-BE) Interface 안에서도 default 를 사용하면 body 가 있는 일반적인 메소드 정의 가능
         *         다른 Class 들은 필요에 따라 abstract method 만 override 할 수도 있고
         *         default method 도 override 할 수 있음. 즉 AS-IS 처럼 abstract Class 를 따로 둘 필요가 없어짐짐
         *
         *  +) extends 는 하나의 Class 만 가능하고, implements 는 여러 개의 Interface 참조가 가능
         *     따라서, AS-IS 엔 abstract Class 를 두어 해당 Class 하나만 extends 할 수 있었다면
         *            TO-BE 에선 default 메소드를 가진 여러 Interface 를 implements 하여 AS-IS 와 동일하게 사용 가능해짐
         */

        /*
         WebMvcConfigurerAdapter Class 가 WebMvcConfigurer Interface 메소드를 그대로 가지고 있는 abstract Class 역할을 했었으나
         default 사용이 가능해지고 WebMvcConfigurer 에서 각 메소드를 직접 default 로 구현하면서
         WebMvcConfigurerAdapter 의 필요성이 사라짐  (현재 deprecated 처리 됨)
         */

        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer(){
        };

        WebMvcConfigurerAdapter webMvcConfigurerAdapter = new WebMvcConfigurerAdapter() {
        };



        /**
         * 자바 8에서 추가한 기본 메소드로 인한 API 변화
         * Iterable, Collection, Comparator 의 기본 메소드 사용 예제
          */
        // 예제 1. Iterable - forEach(Consumer <? super String> action) ------------------------------------------------------------
        System.out.println("예제 1. Iterable - forEach()");       // forEach(Consumer <? super String> action)
        System.out.println("1-1. forEach() - 일반적인 Lambda 형식으로 출력하기");
        color.forEach(s -> {
            System.out.println(s);
        });

        System.out.println("1-2. forEach() - 메소드 레퍼런스를 활용한 Lambda 형식으로 출력하기");
        color.forEach(System.out::println);

        System.out.println("1-3. 번외 - for문 사용하기");
        for (String s : color){
            System.out.println(s);
        }


      // 예제 2. Iterable - spliterator() -------------------------------------------------------
        System.out.println("예제 2. Iterable - spliterator()");       // 문자열 쪼개는데 사용
        Spliterator<String> spliterator1 = color.spliterator();
        Spliterator<String> spliterator2 = spliterator1.trySplit();    // 알아서 반절씩 쪼갬. spliterator1에서 앞에 반절을 가져가고 이후 2에서 trySplit으로 나머지 가능

        while(spliterator1.tryAdvance(System.out::println));
        System.out.println("============================");
        while(spliterator2.tryAdvance(System.out::println));


      // 예제 3. Collection - stream() -----------------------------------------------------------
        System.out.println("예제 3. Collection - stream()");          // spliterator 를 참조하고 있는 메소드 (이후 더 자세히..)
        color.stream().map(String::toUpperCase)
                     .filter( s -> s.startsWith("w"))   // 필터링하기
                     .collect(Collectors.toSet());      // 모아오기


      // 예제 4. Collection - removeIf(Predicate <? super E> filter) ----------------------------
        System.out.println("예제 4. Collection - removeIf()");        // 괄호 안 조건에 부합하는 것을 지우는데 사용
        color.removeIf(s -> s.startsWith("w"));     // removeIf(Predicate <? super E> filter);
        color.forEach(System.out::println);


      // 예제 5. Comparator - sort(Comparator <? super E> c) --------------------------------------
        System.out.println("예제 5. Comparator - sort()");            // 정렬하는데 주로 사용
        System.out.println("5-1. 일반적인 순서로 정렬");
        color.sort(String::compareToIgnoreCase);    // sort(Comparator <? super E> c) - sort 함수 안에 비교 조건?을 명시
        color.forEach(System.out::println);         // compareToIgnoreCase 순서대로 정렬하기

        System.out.println("5-2. 역순으로 정렬");
        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        color.sort(compareToIgnoreCase.reversed());    // compareToIgnoreCase.reversed() 역순으로 정렬하기
        color.forEach(System.out::println);



    }
}
