package study.java8to11;

public interface Printer {

    /**
     *    1. default 메소드 란?
     *     Interface 에서 구현이 된 method 를 추가하고 싶은 경우,
     *     또는 뒤늦게 메소드를 추가하려는데 추가 시 참조해 간 모든 구현체를 손봐야 하는 경우 등에 사용
     *     기본적으로 Interface 에서 Body 영역을 구현하려 하면 아래와 같은 오류 메세지 발생
     *     Interface abstract methods cannot have body
     *     (ex.
     *         void printNotDefaultMethod(){
     *             System.out.println("I'm default method!");
     *         }
     *     )
     *
     *
     *    2. Object 객체의 method 명으로는 default 메소드 구현 불가
     *     Default method 'toString' overrides a member of 'java.lang.Object'
     *     (ex.
     *       default String toString() {
     *
     *       }
     *      )
     *
     *
     *    3. default 적용 시 유의 점
     *     가령 아래 예제처럼 getName() 을 사용할 경우,
     *     Interface 를 참조해 간 다른 구현체들에서 getName() 을 Null Return 이 가능하게 구현했다면
     *     getName() 을 사용한 printUpperName() 에서 에러가 발생할 수 있다.
     *     -> @ImplSpec Annotation을 사용해 구현 내용 문서화 할 것을 권장
     *
     *
     *    4. static 메소드도 사용 가능
     *     static 메소드 안에서는 Non-static 메소드는 사용 불가. 따라서 getName() 도 사용 불가
     *
     *
     */

    void printName();

    String getName();

    /**
     * @ImplSpec 이 구현체는 getName() 으로 가져온 문자열을 대문자로 바꿔 출력한다.
     */
    default void printUpperName(){     // default 를 붙이면 body 를 가진 구현체 메소드 사용 가능
        System.out.println("  printMethod() : Printer 에서 default 로 구현됨  /  결과는 "
                + getName().toUpperCase());     // getName() 으로 Null 이 들어오면 에러발생

    }

    static void printStaticMethod(){
        System.out.println("  printStaticMethod() : Printer 에서 static 로 구현됨");
    }



}
