package study.java8to11;

public class InfMethodExample{

    void testInfMethod(){

      // 예제 1. Interface 의 default method 예제 ----------------------------------
        System.out.println("예제 1. Interface 의 default method 예제");

        PrintImpl print = new PrintImpl("jieun");     // Print를 Impl 받아 구현한 Class
        print.printName();                                  // Class 에서 override 로 구현한 메소드
        print.printUpperName();                             // Interface 에서 default 로 이미 구현한 메소드
        Printer.printStaticMethod();                        // Interface 에서 static 으로 이미 구현한 메소드
    }

}
