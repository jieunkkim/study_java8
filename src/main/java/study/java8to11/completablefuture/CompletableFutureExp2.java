package study.java8to11.completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class CompletableFutureExp2 {

    public void testCompletableFutureExp2() throws ExecutionException, InterruptedException {
        /**
         * CompletableFuture
         *
         * CompletableFuture 를 사용해서 여러 작업 조합하는 방법 예제
         * CompletableFuture 관련 예외 처리 방법 예제
         *
         *
         */
       // 예제 1. future 간의 순서 (또는 의존성) 이 존재하는 경우 작업 조합하기 (thenCompose)
        System.out.println("예제 1. future 간의 순서 (또는 의존성) 이 존재하는 경우 작업 조합하기 (thenCompose)");
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> future;
        future = hello.thenCompose(s -> getWorld(s));    // hello return 값인 s 를 getWorld() 의 매개변수로 넘겨 작업 연결
        System.out.println(future.get());


       // 예제 2. 의존성이 없이 동시 수행 필요한 경우 작업 조합하기 (thenCombine)
        System.out.println("\n예제 2. 의존성이 없이 동시 수행 필요한 경우 작업 조합하기 (thenCombine)");

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        });

        CompletableFuture<String> future2;
        future2 = hello.thenCombine(world, (h,w) -> h + " " + w);  // lambda BiFunction
        System.out.println(future2.get());

      // 예제 3. 2개 이상일 때 모든 Subtask 를 합쳐서 실행하는 방법 (allOf, anyOf)
        System.out.println("\n예제 3. 2개 이상일 때 모든 Subtask 를 합쳐서 실행하는 방법 (allOf)");

        // allOf 로 전체 결과 받아 출력하기 (복잡한 방법.. 이해가 잘 가지 않음)
        List<CompletableFuture<String>> futureList = Arrays.asList(hello, world);

        CompletableFuture[] futures = futureList.toArray(new CompletableFuture[futureList.size()]);
        CompletableFuture<List<String>> result = CompletableFuture.allOf(futures)
                .thenApply(v -> {
                    return futureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
                });

        result.get().forEach(System.out::println);

        // anyOf 로 아무 결과나 받아 하나만 출력하기
        CompletableFuture<Void> future3 = CompletableFuture.anyOf(world, hello)
                .thenAccept(System.out::println);
        future3.get();


      // 예제 4. 예외 처리하기 (exceptionally, handle)
        System.out.println("\n예제 4. 예외 처리하기 (exceptionally, handle)");

        boolean throwError = true;

        // exceptionally 사용 예제
        hello = CompletableFuture.supplyAsync(() ->  {
            if(throwError) {
                throw new IllegalStateException(); // 에러 강제 발생
            }

            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).exceptionally(ex -> {    // Error 발생했을 때 후속 처리 필요한 Function 구현 가능
            System.out.println(ex);
           return "Error!";
        });
        System.out.println(hello.get());

        // handle 사용 예제
        hello = CompletableFuture.supplyAsync(() ->  {
            if(!throwError) {
                throw new IllegalStateException(); // 에러 강제 발생
            }

            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).handle((r, ex) -> {    // r 은 정상적으로 받아온 결과값, ex 는 Error 발생 시 받은 결과
            if(ex != null){
                System.out.println(ex);
                return "Error!";
            }
            return r;
        });
        System.out.println(hello.get());



    }

    private static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return message + " World";
        });
    }
}
