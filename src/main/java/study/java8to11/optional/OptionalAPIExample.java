package study.java8to11.optional;

import study.java8to11.stream.OnlineClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalAPIExample {

    public void testOptionalAPI(){

        List<NewOnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new NewOnlineClass(1, "spring boot", true));
        springClasses.add(new NewOnlineClass(5, "rest api development", false));

      // 예제 1. Optional 에 값이 있는지 확인하기 (isPresent, isEmpty)
        System.out.println("예제 1. Optional 에 값이 있는지 확인하기 (isPresent, isEmpty)");
        Optional<NewOnlineClass> optional = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("jpa"))
                .findFirst();   // jpa 란 값을 가진 과정이 없으므로 Optional

        System.out.println(optional.isPresent());
        System.out.println(optional.isEmpty());


      // 예제 2. Optional 값 꺼내오기 (get)
        System.out.println("\n예제 2. Optional 값 꺼내오기 (get)");

        // 값이 있을 때
        optional = springClasses.stream().filter(oc -> oc.getTitle().startsWith("spring")).findFirst();
        NewOnlineClass spring = optional.get();
        System.out.println(spring.getTitle());

        // 값이 없을 때 - NoSuchElementException 발생
/*
        optional = springClasses.stream().filter(oc -> oc.getTitle().startsWith("jpa")).findFirst();
        NewOnlineClass jpa = optional.get();
        System.out.println(jpa.getTitle());
*/


      // 예제 3. Optional 에 값이 있는 경우에 그 값을 가지고 ~~를 하라. (ifPresent)
        System.out.println("\n예제 3. Optional에 값이 있는 경우에 그 값을 가지고 ~~를 하라. (ifPresent)");

        optional = springClasses.stream().filter(oc -> oc.getTitle().startsWith("spring")).findFirst();
        optional.ifPresent(oc -> System.out.println(oc.getTitle()));

        optional = springClasses.stream().filter(oc -> oc.getTitle().startsWith("jpa")).findFirst();
        optional.ifPresent(oc -> System.out.println(oc.getTitle()));


      // 예제 4. Optional 에 값이 있으면 가져오고 없는 경우에 ~~를 리턴하라. (orElse(T))
        System.out.println("\n예제 4. Optional에 값이 있으면 가져오고 없는 경우에 ~~를 리턴하라. (orElse(T))");

        optional = springClasses.stream().filter(oc -> oc.getTitle().startsWith("jpa")).findFirst();

        // optional 에 값이 없으면 (orElse) createNewClass() 결과를 리턴하라
        // 단점 ) optional 에 값이 있어도 orElse 내부를 실행시킴
        NewOnlineClass jpa = optional.orElse(createNewClass());
        System.out.println(jpa.getTitle());



      // 예제 5. Optional 에 값이 있으면 가져오고 없는 경우에 ~~를 하라. (orElseGet(supplier))
        System.out.println("\n예제 5. Optional 에 값이 있으면 가져오고 없는 경우에 ~~를 하라. (orElseGet(supplier))");

        optional = springClasses.stream().filter(oc -> oc.getTitle().startsWith("jpa")).findFirst();

        // optional 에 값이 없으면 orElseGet 내부 실행 (? Lazy 하게 처리 가능? )
        NewOnlineClass jpa2 = optional.orElseGet(OptionalAPIExample::createNewClass);
        System.out.println(jpa2.getTitle());


      // 예제 6. Optional 에 값이 있으면 가졍고 없는 경우 에러를 던져라. (orElseThrow)
        System.out.println("\n예제 6. Optional 에 값이 있으면 가졍고 없는 경우 에러를 던져라. (orElseThrow)");

        optional = springClasses.stream().filter(oc -> oc.getTitle().startsWith("jpa")).findFirst();

        //NewOnlineClass jpa3 = optional.orElseThrow(IllegalStateException::new);
        //System.out.println(jpa3.getTitle());


      // 예제 7. Optional 에 들어있는 값 걸러내기 (filter)
        System.out.println("\n예제 7. Optional 에 들어있는 값 걸러내기 (filter)");

        optional = springClasses.stream().filter(oc -> oc.getTitle().startsWith("spring")).findFirst();

        // filter 의 결과로도 Optional 이 반환될 수 있음
        Optional<NewOnlineClass> newClass = optional
                .filter(noc -> !noc.isClosed());
        System.out.println(newClass.isEmpty());



      // 예제 8. Optional 에 들어있는 값 변환하기 (map, flatMap) (모르겠다)
        System.out.println("\n예제 8. Optional 에 들어있는 값 변환하기 (map, flatMap)");

        optional = springClasses.stream().filter(oc -> oc.getTitle().startsWith("spring")).findFirst();

        // map 안에 리턴되는 값이 Optional 이 아니면 그냥 꺼내면 됨.
        System.out.println(optional.map(NewOnlineClass::getId).get());

        // map 안에 리턴되는 값이 Optional 이 flatMap 을 사용하여 꺼낼 수 있음.
        // flatMap 사용 전
        Optional<Optional<Progress>> progress = optional.map(NewOnlineClass::getProgress);
        Optional<Progress> progress1 = progress.orElseThrow();

        // flatMap 사용 전 (Stream 이 제공하는 flatMap 과 Optional 이 제공하는 flatMap 은 다르다 )
        // Stream 의 flatMap 은 요소가 여러 가지 있음 (안에 있는 걸 다 꺼내는...)
        // Optional 의 flatMap 은 양파 껍질 까듯 안에 있는 것을 얻기 위해 사용 (1:1 Mapping?)
        Optional<Progress> progress2 = optional.flatMap(NewOnlineClass::getProgress);
        progress2.orElseThrow();


    }

    private static NewOnlineClass createNewClass() {
        return new NewOnlineClass(10, "New Class", false);
    }
}
