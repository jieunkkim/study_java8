package study.java8to11;

public class LambdaExample {

    void testLamdba(){
        // 예제 1. System.out을 활용한 FunctionalInterface + LambdaExample -------------------------------
        System.out.println("예제 1. System.out을 활용한 FunctionalInterface + LambdaExample");
        // 익명 내부 클래스 = anonymous inner class
        RunVoid runVoid = new RunVoid() {
            @Override
            public void doIt() {
                System.out.println("1. runVoid 은 익명 내부 클래스");

            }
        };

        // LambdaExample 한 줄 (인라인)
        RunVoid runVoid1 = () -> System.out.println("2. runSomething2는 LambdaExample 한 줄");

        // LambdaExample 여러 줄
        RunVoid runVoid2 = () -> {
            System.out.println("3. runSomething2는");
            System.out.println("    LambdaExample 여러 줄");
        };

        runVoid.doIt();
        runVoid1.doIt();
        runVoid2.doIt();

        // 예제 2. Return 값이 있는 FunctionalInterface + LambdaExample --------------------------------
        System.out.println("\n예제 2. Return 값이 있는 FunctionalInterface + LambdaExample");

        RunInt runInt = new RunInt() {
            @Override
            public int doIt(int number) {
                return number + 100;
            }
        };

        RunInt runInt1 = (number) -> number+100;

        RunInt runInt2 = (number) -> {
            return number + 100;
        };

        System.out.println("1. runInt.doIt(1) ? " + runInt.doIt(1));
        System.out.println("2. runInt.doIt(2) ? " + runInt.doIt(2));
        System.out.println("3. runInt.doIt(3) ? " + runInt.doIt(3));


        // 예제 3. 함수 밖에 있는 값에 의존된다면 순수 함수(Pure Function) 라고 보기 어렵다---------------
        System.out.println("\n예제 3. 함수 밖에 있는 값에 의존된다면 순수 함수(Pure Function) 라고 보기 어렵다");

        RunInt runIntNotPure1 = new RunInt() {
            int base = 100;
            @Override
            public int doIt(int number) {
                return base + number;
            }
        };

        RunInt runIntNotPure2 = new RunInt() {
            int base = 1000;
            @Override
            public int doIt(int number) {
                return base + number;
            }
        };

        System.out.println("1. runIntNotPure1.doIt(1) ? " + runIntNotPure1.doIt(1));
        System.out.println("2. runIntNotPure2.doIt(1) ? " + runIntNotPure2.doIt(1));
    }
}
