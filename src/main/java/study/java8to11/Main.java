package study.java8to11;

import study.java8to11.completablefuture.CompletableMain;
import study.java8to11.datetime.DateTimeExample;
import study.java8to11.datetime.DateTimeMain;
import study.java8to11.lambda.*;
import study.java8to11.optional.OptionalMain;
import study.java8to11.stream.StreamMain;

import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

    // 함수형 인퍼테이스와 람다 , 인퍼테이스의 변화
      LambdaMain lambdaMain = new LambdaMain();

    // Stream
      StreamMain streamMain = new StreamMain();

    // Optional
      OptionalMain optionalMain = new OptionalMain();

    // Date 와 Time
      DateTimeMain dateTimeMain = new DateTimeMain();

    // CompletableFuture
      CompletableMain completableMain = new CompletableMain();

      /*
      lambdaMain.lambdaMain();
      streamMain.streamMain();
      optionalMain.optionalMain();
      dateTimeMain.datetimeMain();
      */

        completableMain.completableMain();


    }

}
