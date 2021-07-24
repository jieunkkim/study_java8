package study.java8to11.lambda;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class MethodRefExample {
    void testMethodRef(){
      // 예제 1. static 메소드 레퍼런스 사용법 --------------------------------------
        System.out.println("예제 1. static 메소드 레퍼런스 사용법");
        UnaryOperator<String> unaryOperator1 = StringPrinter::printHi;
        UnaryOperator<String> unaryOperator2 = (name) -> "Hi " + name;

        System.out.print("  1-1. UnaryOperator<String> unaryOperator1 = StringPrinter::printHi   /  ");
        System.out.println(unaryOperator1.apply("World"));
        System.out.print("  1-2. UnaryOperator<String> unaryOperator2 = (name) -> \"Hi \" + name   /  ");
        System.out.println(unaryOperator2.apply("World"));


      // 예제 2. Non-static 인스턴스 메소드 레퍼런스 사용법 --------------------------------------
        System.out.println("\n예제 2. Non-static 메소드 레퍼런스 사용법");
        StringPrinter stringPrinter = new StringPrinter();
        UnaryOperator<String> unaryOperator3 = stringPrinter::printHello;

        System.out.print("  2-1. UnaryOperator<String> unaryOperator = stringPrinter::printHello   /  ");
        System.out.println(unaryOperator3.apply("World"));


      // 예제 3. 입력값 없는 생성자 레퍼런스 사용법 (Supplier)  --------------------------------------
        System.out.println("\n예제 3. 입력값 없는 생성자 레퍼런스 사용법 (Supplier)");
        Supplier<StringPrinter> supplier = StringPrinter::new;

        System.out.println("  3-1. Supplier<StringPrinter> supplier = StringPrinter::new  / ");
        supplier.get().getName();  // 이 순간 참조한 StringPrinter 생성됨.


      // 예제 4. 입력값 있는 생성자 레퍼런스 사용법 (Function)  --------------------------------------
        System.out.println("\n예제 4. 입력값 있는 생성자 레퍼런스 사용법 (Function)");
        Function<String, StringPrinter> function = StringPrinter::new;

        System.out.print("  4-1. Function<String, StringPrinter> function = StringPrinter::new  / ");
        System.out.println(function.apply("World").getName());  // 이 순간 참조한 StringPrinter 생성됨.


      // 예제 5. 임의 객체의 인스턴스 메소드 레퍼런스 사용법  --------------------------------------
        System.out.println("\n예제 5. 임의 객체의 인스턴스 메소드 레퍼런스 사용법");

        String[] colors = {"white", "red", "blue"};
        Arrays.sort(colors, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(colors));


    }
}
