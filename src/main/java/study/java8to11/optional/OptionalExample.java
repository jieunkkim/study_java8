package study.java8to11.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalExample {

    public void testOptional(){

        /**
         * Optional
         * Null Object 를 처리할 때 사용하는 컨테이너
         * 오직 값 한개가 들어있을 수도 없을 수도 있는 컨테이너
         *
         * 1. Return Type 으로만 쓰기를 권장 (메소드 매개변수, Map 의 Key, 인스턴스 필드 타입으로 쓰지 말자)
         *      . Map 의 Key 는 Null 이 아닌 것이 원칙
         *      . Domain 필드 자체가 있을 수도 없을 수도 있다는 것은 적합하지 않음
         * 2. 프리미티브 타입용 Optional 은 따로 있다.
         *      . Optional.of(10); -> Boxing 처리 되어 unBoxing 이 필요하기 때문에
         *        OptionalInt.of(10); 와 같은 프리미티브 타입용 Optional 을 사용하자
         * 3. Optional 을 리턴하는 메소드에서 Null 을 리턴하지 말자
         *      . 리턴할 것이 없다면 null 이 아닌 Optional.empty(); 를 리턴해야 한다.
         * 4. Collection, Map, Stream Array, Optional 은 Optional 로 감싸지 말 것.
         *      . 이미 비어있다는 것을 표현할 수 있는 Object 들 이므로 굳이 Optional 로 감쌀 필요가 없다.
         */
        List<NewOnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new NewOnlineClass(1, "spring boot", true));
        springClasses.add(new NewOnlineClass(2, "spring data java", true));
        springClasses.add(new NewOnlineClass(3, "spring mvc", false));
        springClasses.add(new NewOnlineClass(4, "spring core", false));
        springClasses.add(new NewOnlineClass(5, "spring api development", false));

        NewOnlineClass spring_boot = new NewOnlineClass(1, "spring boot", true);
        //Duration studyDuration = spring_boot.getProgress().getStudyDuration();
        //System.out.println(studyDuration);  // Null pointException 발생 (getProgress 결과가 Null 인데, getStudyDuration 로 Null 을 참조해선 안됨)

        Optional<Progress> progress = spring_boot.getProgress();
        System.out.println(progress);
    }
}
