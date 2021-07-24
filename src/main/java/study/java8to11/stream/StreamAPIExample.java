package study.java8to11.stream;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPIExample {

    public void testStreamAPI(){

        /**
         * Stream 사용해보기 (filter, match, collect, map, flatMap, iterate.. )
         *
         * Stream method 사용 시 해당 method 의 input / output Type 을 잘 살펴보자!
         * (따로 type 을 바꾸지 않았다면, stream 수행한 대상의 element Type 이 자동 지정됨 (super Type)
         *  ex. springClasses.stream() -> OnlineClass Type )
         */
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data java", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "spring api development", false));


        System.out.println("예제 1. SpringClass 에서 spring 으로 시작하는 수업");
        springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .forEach(oc -> System.out.print(oc.getId() +"/"));


        System.out.println("\n\n예제 2. SpringClass 에서 close 되지 않은 수업");
        springClasses.stream()
                //.filter(oc -> !oc.isClosed())
                .filter(Predicate.not(OnlineClass::isClosed))       // Method Reference 응용 버전
                .forEach(oc -> System.out.print(oc.getId() +"/"));


        System.out.println("\n\n예제 3. SpringClass 에서 수업 이름만 모아서 Stream 만들기");
        springClasses.stream()
                .map(oc -> oc.getTitle())
                .forEach(System.out::println);



        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java, Test", true));
        javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));

        List<List<OnlineClass>> events = new ArrayList<>();
        events.add(springClasses);
        events.add(javaClasses);


        System.out.println("\n예제 4. 두 수업 목록에 들어있는 모든 수업 아이디 출력");
        events.stream()                         // events 의 stream() 결과는 springClasses, javaClasses
                .flatMap(Collection::stream)    // 각 List<OnlineClass> 를 stream() 하면 그 결과는 각 OnlineClass
                .forEach(oc -> System.out.print(oc.getId() + "/"));


        System.out.println("\n예제 5. 10 부터 1 씩 증가하는 무제한 Stream 중에서 앞에 10 개 빼고 최대 10 개 까지만");
        Stream.iterate(10, i -> i + 1)            // 여기까지만 구현하면, 아무 일도 일어나지 않음 (종료 오퍼레이션이 없으므로)
                .skip(10)                               // 앞에 10 개 빼기
                .limit(10)                              // 최대 10 개 제한
                .forEach(System.out::println);


        System.out.println("\n예제 6. 자바 수업 중에 Test 가 들어있는 수업이 있는지 확인");
        boolean test = javaClasses.stream()
                                    .anyMatch(oc -> oc.getTitle().contains("Test"));
        System.out.println(test);


        System.out.println("\n예제 7. Spring 수업 중에 제목에 spring 이 들어간 제목만 모아서 List 로 만들기");
        List<String> spring = springClasses.stream()
                .filter(oc -> oc.getTitle().contains("spring"))
                .map(OnlineClass::getTitle)
                .collect(Collectors.toList());

        spring.forEach(System.out::println);

    }
}
