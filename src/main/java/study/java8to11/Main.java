package study.java8to11;

import study.java8to11.lambda.*;
import study.java8to11.optional.OptionalMain;
import study.java8to11.stream.StreamMain;

public class Main {

    public static void main(String[] args) {

    // 함수형 인퍼테이스와 람다 , 인퍼테이스의 변화
      LambdaMain lambdaMain = new LambdaMain();

    // Stream
      StreamMain streamMain = new StreamMain();

    // Optional
      OptionalMain optionalMain = new OptionalMain();

      /*
      lambdaMain.lambdaMain();
      streamMain.streamMain();
      */

      optionalMain.optionalMain();
    }

}
