package study.java8to11.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExample {

    public void testStream(){
        /**
         * Stream
         * operator 들을 통해 elements 들을 순서대로 또는 병렬로 처리 가능하게 하는 것.
         * Sequence of elements supporting sequential and parallel aggregate operation
         *
         *  1. 데이터를 담고 있는 저장소(컬렉션)이 아니다
         *  2. Stream 이 처리하는 데이터의 소스 (원본 데이터) 를 변경하지 않는다.
         *  3. Stream 으로 처리하는 데이터는 오직 한번만 처리한다
         *  4. 무제한 일 수도 있다 (Short Circuit 메소드를 사용해서 제한할 수 있다)
         *      . 들어오는 데이터를 무제한으로 처리할 수 있지만, Short Circuit 을 통해 일부만 처리하도록 제한 가능
         *  5. 중개 오퍼레이션은 근본적으로 lazy 하다
         *      . 중개 오퍼레이션 : Stream 을 리턴한다 (filter, map, limit, skip, sorted, ...)
         *      . 종료 오퍼레이션 : Stream 을 리턴하지 않는다. (collect, allMatch, count, forEach, min, max, ...)
         *  6. 손쉽게 병렬처리 할 수 있다.
         */
        List<String> colors = new ArrayList<>();

        colors.add("red");
        colors.add("blue");
        colors.add("yellow");
        colors.add("orange");

      // 예제 1. Stream 이 처리하는 데이터의 소스를 변경하지 않는다. (위 2번 항목)
        System.out.println("예제 1. Stream 이 처리하는 데이터의 소스를 변경하지 않는다.");

        Stream<String> streamColors = colors.stream().map(String::toUpperCase);

        colors.forEach(System.out::println);
        System.out.println("===========원본 소스 출력 / Stream 출력===============");
        streamColors.forEach(System.out::println);

      // 예제 2. 중개 오퍼레이션은 근본적으로 lazy 하다. (위 5번 항목)
        System.out.println("\n예제 2. 중개 오퍼레이션은 근본적으로 lazy 하다.");

        // Return 이 Stream 으로 된 것 = 중개 오퍼레이터인 상태
        Stream<String> streamColors2 = colors.stream().map(String::toUpperCase);

        // 출력되지 않음. 종료형 오퍼레이터가 실행되기 전까지 실행하지 않음 (종료형 오퍼레이터를 기다리는 상태)
        colors.stream().map(s -> {
            System.out.println(s);
            return s.toUpperCase();
        });

        // 출력 됨 (대문자로 변환되지 않은 상태로) . collect 라는 종료형 오퍼레이터를 만났기 때문에 출력은 됨.
        System.out.println("==============================================");
        colors.stream().map(s -> {
            System.out.println(s);
            return s.toUpperCase();
        }).collect(Collectors.toList());

        // 출력 됨 . collect 라는 종료형 오퍼레이터를 만나서 내부 println 도 실행되었고, stream 통해 변환된 객체 대문자 출력도 확인함
        System.out.println("==============================================");
        List<String> collect = colors.stream().map(s -> {
            System.out.println(s);
            return s.toUpperCase();
        }).collect(Collectors.toList());

        collect.forEach(System.out::println);

      // 예제 3. 손쉽게 병렬처리가 가능하다. (위 6번 항목)
        System.out.println("\n예제 3. 손쉽게 병렬처리가 가능하다.");

        // Stream 사용 전 Loop 사용 (병렬 처리하기 어려움)
        List<String> list = new ArrayList<>();
        for(String s : colors){
            System.out.println(s +" " + Thread.currentThread().getName());      // 같은 Thread
            list.add(s.toUpperCase());
        }
        list.forEach(System.out::println);

        System.out.println("==============================================");
        // Stream 사용 전 Loop 사용
        List<String> collect2 = colors.parallelStream().map((s)->{
            System.out.println(s +" " + Thread.currentThread().getName());      // 서로 다른 Thread 로 병렬처리. 병렬이 무조건 빠른건 아니다. (Thread 생성, Context Switching 등 때문에)
            return s.toUpperCase();
        }).collect(Collectors.toList());
        collect2.forEach(System.out::println);
    }
}
