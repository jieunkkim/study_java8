/**
 Java에서 제공하는 Function Interface
 - Interface명 < Input Type, Return Type >

 참고 Docs : https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html
 Reudn
 **/
package study.java8to11.lambda;

import java.util.function.Function;

public class RunIntFuc implements Function<Integer, Integer> {
    @Override
    public Integer apply(Integer integer) {
        return integer + 1000;
    }
}
