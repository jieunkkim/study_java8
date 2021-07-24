package study.java8to11.datetime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTimeExample {

    public void testDateTime() throws InterruptedException {
        /**
         * 기존 날짜/시간 API 의 문제점
         * 1. 클래스 이름이 명확하지 않다
         *      . Date : 날짜 클래스인데 시간까지 다룸 (Timestamp 라고 생각하면 됨)
         *      . date.getTime();
         * 2. 객체가 mutable 하다 (값을 바꿀 수 있다)
         *      . Multi Thread 환경에서 안전하지 않다. (Thread Safe 하지 않다)
         *      . 여러 Thread 를 다루는 환경에서 Date 수정 작업이 이루어 진다면, Thread 가 실행되는 순서에 따라 값이 달라질 수 있음
         * 3. 버그 발생할 여지가 많다
         *      . Type Safe 하지 않다 (Calendar 생성자 매개변수가 모두 int 형으로 어떤 숫자도 들어올 수 있다)
         *      . Calendar 의 경우 month 가 0 부터 시작해서 7 월 입력을 할 경우 6 이란 값을 넣어야 함
         *
         * 이러한 문제때문에 이제까지는 복잡한 날짜/시간 처리엔 Joda Time 을 사용했었다.
         *
         * Java 8 에서는 아래 디자인 철학에 맞춰 개발된 새로운 날짜/시간 API 를 제공한다
         * - Clear, Fluent, Immutable, Extensible
         */
        Date date = new Date();
        long time = date.getTime();

        System.out.println(date);           // Sat Jul 24 16:56:08 KST 2021
        System.out.println(time);           // 출력 결과 1627113368803

        Thread.sleep(1000*3);
        Date after3Seconds = new Date();
        System.out.println(after3Seconds);  // Sat Jul 24 16:56:11 KST 2021

        after3Seconds.setTime(time);        // setTime 하면 기존에 있던 after3Seconds 컬럼 값을 바꿀 수 있음 (mutable 하다)
        System.out.println(after3Seconds);  // Sat Jul 24 16:56:08 KST 2021

        //-----------------------------------------------------------------------------------------

        Calendar calendar = new GregorianCalendar(2021, 7, 24);
        Calendar newCalendar = new GregorianCalendar(2021, Calendar.JULY, 24);
        // month 가 0 부터 시작함. 7 월을 넣으려면 6 을 넣어야 함

        System.out.println(calendar.getTime());
        System.out.println(newCalendar.getTime());


    }
}
