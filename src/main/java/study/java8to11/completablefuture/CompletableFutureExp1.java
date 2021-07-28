package study.java8to11.completablefuture;

import java.sql.SQLOutput;
import java.util.concurrent.*;

public class CompletableFutureExp1 {
    public void testCompletableFuture1() throws ExecutionException, InterruptedException {
        /**
         * CompletableFuture
         *
         * CompletableFuture 를 사용해서 Return 값 받고, Callback 처리하는 방법 예제
         *
         * 예제 1. CompletableFuture 를 사용하여 Future 기본값 셋팅하기 (complete, completedFuture)
         * 예제 2. Return 이 없는 작업 (runAsync), 있는 작업 (supplyAsync)
         * 예제 3. CompletableFuture 로 받은 값 Callback 수행하기 (thenApply, thenAccept, thenRun)
         * 예제 4. Executors 로 생성한 ThreadPool 을 supplyAsync 의 매개변수로 넘겨 사용하기
         *        . Executors 로 ThreadPool 을 만들지 않아도 아래 수행이 가능했던 이유 -> ForkJoinPool 통해 자동으로 ThreadPool 생성, 사용 가능
         */

      // 예제 1. CompletableFuture 를 사용하여 Future 기본값 셋팅하기 (complete, completedFuture)
        System.out.println("예제 1. CompletableFuture 를 사용하여 Future 기본값 셋팅하기 (complete, completedFuture)");

        CompletableFuture<String> future = new CompletableFuture<>();
        future.complete("default value");   // future 의 기본값을 정해주면서 작업을 끝냄
        System.out.println(future.get());

        CompletableFuture<String> future2 = CompletableFuture.completedFuture("default value");
        System.out.println(future2.get());

      // 예제 2. Return 이 없는 작업 (runAsync), 있는 작업 (supplyAsync)
        System.out.println("\n예제 2. Return 이 없는 작업 (runAsync), 있는 작업 (supplyAsync)");

        CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> {    // lambda Runnable
            System.out.println("Hello (Return 없음) " + Thread.currentThread().getName());
        });
        System.out.println(future3.get());


        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> {   // lambda Supplier
            System.out.println("Hello (Return 있음) " + Thread.currentThread().getName());
            return "Hello (Return 있음)";
        });
        System.out.println(future4.get());

     // 예제 3. CompletableFuture 로 받은 값 Callback 수행하기 (thenApply, thenAccept, thenRun)
        System.out.println("\n예제 3. CompletableFuture 로 받은 값 Callback 수행하기 (thenApply, thenAccept, thenRun)");
        CompletableFuture<String> future5 = CompletableFuture.supplyAsync(() -> {   // lambda Supplier
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenApply((s) -> {           // get() 호출 전에 thenApply 를 통해 Callback 가능 (후속으로 수행할 Function 등 구현가능)
            System.out.println(Thread.currentThread().getName());
            return s.toUpperCase();     // return 값이 있을 때 thenApply 사용
        });
        System.out.println(future5.get());

        CompletableFuture<Void> future6 = CompletableFuture.supplyAsync(() -> {   // lambda Supplier
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenAccept((s) -> {           // thenAccept 를 사용하면 return 없이 구현 가능
            System.out.println(Thread.currentThread().getName());
            System.out.println(s.toUpperCase());    // return 값이 없을 때 thenAccept 사용
        });
        future6.get();

        CompletableFuture<Void> future7 = CompletableFuture.supplyAsync(() -> {   // lambda Supplier
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenRun(() -> {           // thenRun() 을 사용하면 매개변수를 주지 않을 수도 있음
            System.out.println(Thread.currentThread().getName());
        });
        future7.get();

      // 예제 4. Executors 로 생성한 ThreadPool 을 supplyAsync 의 매개변수로 넘겨 사용하기
        System.out.println("\n예제 4. Executors 로 생성한 ThreadPool 을 supplyAsync 의 매개변수로 넘겨 사용하기 ");

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture<String> future8 = CompletableFuture.supplyAsync(() -> {   // lambda Supplier
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }, executorService).thenApply((s) -> {           // get() 호출 전에 thenApply 를 통해 Callback 가능 (후속으로 수행할 Function 등 구현가능)
            System.out.println(Thread.currentThread().getName());
            return s.toUpperCase();     // return 값이 있을 때 thenApply 사용
        });
        System.out.println(future8.get());

        executorService.shutdown();


    }
}
