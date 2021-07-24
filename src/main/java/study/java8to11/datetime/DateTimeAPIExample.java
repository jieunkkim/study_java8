package study.java8to11.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateTimeAPIExample {

    public void testDateTimeAPI(){

      // 예제 1. 기계용 Date Time 출력하기 (Instant)
        System.out.println("예제 1. 기계용 Date Time 출력하기 (Instant)");

        Instant instant = Instant.now();    // 현재 기준으로 기계시간을 찍어줌
        System.out.println(instant);        // 그리니치 기준으로 현재 기준시를 출력.
        System.out.println(instant.atZone(ZoneId.of("UTC")));       // UTC 기준 (위와 같음)

        /*  Zone 을 셋팅하면 해당 Zone 기준으로 기준시 출력 가능 */
        ZoneId zone = ZoneId.systemDefault();
        System.out.println(zone);           // 현재 Zone 출력하기

        ZonedDateTime zonedDateTime = instant.atZone(zone);
        System.out.println(zonedDateTime);  // 셋팅한 Zone 기준으로 보기


      // 예제 2. 휴먼용 Date Time 출력하기 (LocalDateTime)
        System.out.println("\n예제 2. 인류용 Date Time 출력하기 (LocalDateTime)");

        LocalDateTime now = LocalDateTime.now();    // 현재 Zone 을 반영 (Local 이 붙어 있는 것을 보아 유추 가능)
        System.out.println(now);

        LocalDateTime birthDay = LocalDateTime.of(1992, Month.DECEMBER, 16, 0, 0, 0);
        System.out.println(birthDay);

        ZonedDateTime nowInAmerica = zonedDateTime.now(ZoneId.of("America/Los_Angeles"));
        System.out.println(nowInAmerica);


      // 예제 3. 기계용 기간을 표현하는 방법 (Duration)
        System.out.println("\n예제 3. 기계용 기간을 표현하는 방법 (Duration)");

        Instant instantNow = Instant.now();
        Instant plus = instantNow.plus(10, ChronoUnit.SECONDS);
        Duration between = Duration.between(instantNow, plus);
        System.out.println(between.getSeconds());


      // 예제 4. 휴먼용 기간을 표현하는 방법 (Period)
        System.out.println("\n예제 4. 휴먼용 기간을 표현하는 방법 (Period)");
        LocalDate today = LocalDate.now();
        LocalDate thisYearBirthday = LocalDate.of(2021, Month.DECEMBER, 16);

        Period period = Period.between(today, thisYearBirthday);
        System.out.println(period.getDays());

        Period until = today.until(thisYearBirthday);
        System.out.println(until.getDays());


      // 예제 5. 날짜/시간 Format 바꾸기 (DateTimeFormatter)
        System.out.println("\n예제 5. 날짜/시간 Format 바꾸기 (DateTimeFormatter)");

        LocalDateTime nowDate = LocalDateTime.now();
        System.out.println(nowDate);        // Formatting 적용 전

        DateTimeFormatter MMddyy    = DateTimeFormatter.ofPattern("MM/dd/yy");
        System.out.println(nowDate.format(MMddyy));     // Formatting 적용 후
        System.out.println(nowDate.format(DateTimeFormatter.BASIC_ISO_DATE));


      // 예제 6. Legacy API 지원 (기존 Date 지원)
        System.out.println("\n예제 6. Legacy API 지원 (기존 Date 지원) ");

        Date date = new Date();
        Instant instant1 = date.toInstant();
        Date newDate = Date.from(instant);          // instant -> date

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        ZonedDateTime dateTime = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault());  // GregorianCalendar -> ZonedDateTime
        GregorianCalendar from = GregorianCalendar.from(dateTime);  // ZonedDateTime -> GregorianCalendar
        System.out.println(dateTime);
        System.out.println(from.getTime());


        ZoneId zoneId = TimeZone.getTimeZone("PST").toZoneId();     // TimeZone -> ZoneId
        TimeZone timeZone = TimeZone.getTimeZone(zoneId);           // ZoneId -> TimeZone
        System.out.println(zoneId);
        System.out.println(timeZone.getID());


    }
}
