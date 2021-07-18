package study.java8to11;

@FunctionalInterface    // 함수형 Interface로 정의 가능
public interface RunInt {

    /*
        함수형 Interface : 추상 method 하나만 있는 경우 (두 개 이상은 안됨)
     */

    int doIt(int number);        // abstract 생략

    static void printName() {       // public 생략, Interface 안에 static 메소드 정의 가능
        System.out.println("Study");
    }

    default void printAge() {
        System.out.println("30");
    }
}
