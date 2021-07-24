package study.java8to11.lambda;

import java.util.function.*;

public class FunctionExample {

    void testFunction(){
      // 예제 1. Function<T,R> 로 구현된 Class 사용해보기 -----------------------------------
        System.out.println("예제 1. Function<T,R> 사용해보기");
        RunIntFuc runIntFuc = new RunIntFuc();
        System.out.println("1. runIntFuc.apply(1) ? " + runIntFuc.apply(1));

      // 예제 2. Function<T,R> 구현 Class 없이 사용해보기 -----------------------------------
        System.out.println("\n예제 2. Function<T,R> 구현 Class 없이 사용해보기");
        Function<Integer, Integer> function = (number) -> number + 10000;
        System.out.println("2. function.apply(1) ? " + function.apply(1));

      // 예제 3. Function<T,R> 조합해서 사용해보기 -----------------------------------
        System.out.println("\n예제 3. Function<T,R> 조합해서 사용해보기");
        Function<Integer, Integer> plus = (number) -> number + 10;
        Function<Integer, Integer> multiply = (number) -> number * 2;

        // compose를 사용하면, 괄호안에 apply 수행한 값을 괄호 밖 apply 매개변수로 사용
        System.out.println("  3-1. plus.compose(multiply).apply(1) ? " + plus.compose(multiply).apply(1));
        System.out.println("  3-2. multiply.compose(plus).apply(1) ? " + multiply.compose(plus).apply(1));

        // andThen을 사용하면, 명시된 순서대로 apply 실행. 즉 괄호 밖 apply 값이 괄호 안 apply 매개변수로 사용됨
        System.out.println("  3-3. plus.andThen(multiply).apply(1) ? " + plus.andThen(multiply).apply(1));
        System.out.println("  3-4. multiply.andThen(plus).apply(1) ? " + multiply.andThen(plus).apply(1));


      // 예제 4. Consumer<T> 사용해보기 -----------------------------------
        System.out.println("\n예제 4. Consumer<T> 사용해보기");

        // Consumer는 Input은 받지만, Return은 따로 하지 않는 Function
        Consumer<Integer> consumer = (number) -> System.out.println(number + "를 받지만 Return은 따로 없습니다");
        consumer.accept(100);

      // 예제 5. Supplier<T> 사용해보기 -----------------------------------
        System.out.println("\n예제 5. Supplier<T> 사용해보기");

        // Supplier는 정의된 Type의 값을 제공하는 Function
        Supplier<Integer> supplier1 = () -> 100;
        System.out.println("  5-1. supplier1.get() + 1 ? " + (supplier1.get() + 1));
        Supplier<String> supplier2 = () -> "100";
        System.out.println("  5-2. supplier2.get() + 1 ? " + (supplier2.get() + 1));


      // 예제 6. Predicate<T> 사용해보기 -----------------------------------
        System.out.println("\n예제 6. Predicate<T> 사용해보기");

        // Predicate는 인자값을 받아 True/False를 Return 해주는 Function
        Predicate<String> equalStr = (str) -> str.equals("Hello");
        System.out.println("  6-1. equalStr.test(\"Hello\")  ? " + equalStr.test("Hello"));
        System.out.println("  6-2. equalStr.test(\"Hello!!!\")  ? " + equalStr.test("Hello!!!"));

      // 예제 7. UnaryOperator<T,R> 사용해보기 -----------------------------------
        System.out.println("\n예제 7. UnaryOperator<T,R> 사용해보기");

        // UnaryOperator는 Input값과 Return값이 동일할 때 사용 가능하다. Function을 상속받았다.
        UnaryOperator<Integer> unaryplus = (number) -> number + 10;
        Function<Integer, Integer> funcplus = (number) -> number + 10;

        System.out.println("  7-1. unaryplus.apply(1) ? " + unaryplus.apply(1));
        System.out.println("  7-2. funcplus.apply(1)  ? " + funcplus.apply(1));

      // 예제 8. BiFunction<T,U,R> 사용해보기 -----------------------------------
        System.out.println("\n예제 8. BiFunction<T,U,R> 사용해보기");

        // BiFunction은 Input을 두 개 받을 수 있는 Function
        BiFunction<Integer, Integer, Integer> biFunction = (num1, num2) -> num1 + num2;
        System.out.println("biFunction.apply(10, 100) ? " + biFunction.apply(10, 100));


      // 예제 9. BinaryOperator<T> 사용해보기 -----------------------------------
        System.out.println("\n예제 9. BinaryOperator<T> 사용해보기");

        // BinaryOperator은 BiFunction에서 Input 2개와 Return들의 Type이 모두 동일할 때 사용 가능하다. Bifunction을 상속받았다.
        BinaryOperator<Integer> binaryOperator = (num1, num2) -> num1 + num2;
        System.out.println("binaryOperator.apply(10, 100) ? " + binaryOperator.apply(10, 100));



    }
}
