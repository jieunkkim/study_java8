package study.java8to11;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class LambdaExpExample {
    void testLambdaExp() {

      // 예제 1. 인자 개수에 따른 Ramda 표현식 -------------------------------
        System.out.println("예제 1. 인자가 없는 Ramda 표현식");
        // 익명 내부 클래스 = anonymous inner class
        Supplier<String> supplier = () -> "  1-1. () -> [body]   or  () -> { [body] }";
        System.out.println(supplier.get());

        Function<String, String> function = (str) -> str;
        System.out.println(function.apply("  1-2. (a) -> [body]   or  (a) -> { [body] }"));

        BinaryOperator<String> binaryOperator = (str1, str2) -> str1 + str2;
        System.out.println(binaryOperator.apply("  1-3. (a, b) -> [body]   ", "or  (a, b) -> { [body] }"));


      // 예제 2. 로컬 클래스/익명 클래스/람다의 Effective final, Shadowing 차이점 -------------------------------
        System.out.println("\n예제 2. 로컬 클래스/익명 클래스/람다의 Effective final, Shadowing 차이점");
        run();


    }

    private void run(){

        /**
            1. effective final
             로컬 클래스, 익명 클래스 등에서 외부 변수를 참조하려면 해당 변수는 final로 선언되어야 하는데
             Java 8 에선 final을 따로 명시하지 않아도 final로 동작 ( = effective final)
             참조로 인해 자동 final 설정되면, 변수 변경도 불가 (final 특징)
             (ex. baseNumber ++)

            2. 로컬,익명 클래스  vs  람다  쉐도잉 차이
               로컬, 익명 클래스는 별도의 Scope 을 가지는 반면,
              람다는 run 과 동일 Scope 를 가지므로 shadowNumber를 매개변수로 사용 불가
              (ex. Consumer<Integer> consumer1 = (shadowNumber) -> System.out.println(baseNumber + shadowNumber);)
         */

        // final 변수 (아래 참조들에 의해 자동으로 final 처리됨)
        int baseNumber = 10;
        int shadowNumber = 10;

        // 로컬 클래스
        class LocalClass {
            void printBaseNumber(){
                int shadowNumber = 11;                          // 11 shadowNumber가 10 shadowNumber를 가려버림 (쉐도잉)
                System.out.println(baseNumber + shadowNumber);  // 로컬 클래스에서 Class 밖 baseNumber 변수 참조
            }
        }
        System.out.print("  2-1. LocalClass 출력 결과 : ");
        new LocalClass().printBaseNumber();

        // 익명 클래스
        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer shadowNumber) {          // shadowNumber가 accept를 통해 받은 11로 셋팅됨. (쉐도잉)
                System.out.println(baseNumber + shadowNumber);  // 익명 클래스에서 Class 밖 baseNumber 변수 참조
            }
        };
        System.out.print("  2-2. Anonymous Class (Consumer) 출력 결과 : ");
        consumer.accept(11);

        // 람다 (쉐도잉 블가)
        Consumer<Integer> consumer1 = (i) -> System.out.println(baseNumber + shadowNumber); // 람다에서 구현영역 밖 baseNumber 변수 참조
        System.out.print("  2-3. Lambda (Consumer) 출력 결과 : ");
        consumer1.accept(1);
    }
}
