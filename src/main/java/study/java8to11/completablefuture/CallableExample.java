package study.java8to11.completablefuture;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CallableExample {
    public void testCallable() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Callable<String> hello = () -> {
            Thread.sleep(3000L);
            return "Hello";
        };

        Callable<String> java = () -> {
            Thread.sleep(2000L);
            return "Java";
        };

        Callable<String> world = () -> {
            Thread.sleep(1000L);
            return "World";
        };

      // 예제 1. Callable 호출하여 Future 로 값 get() 해오기, 그리고 cancel() 해보기
        System.out.println("예제 1. Callable 호출하여 Future 로 값 get() 해오기, 그리고 cancel() 해보기");

        Future<String> helloFuture = executorService.submit(hello);
        System.out.println("helloFuture.isDone()?  " + helloFuture.isDone());       // Thread 상태 검사
        System.out.println("Started!");

//        helloFuture.cancel(true);  // Thread 수행을 Interrupt 해서 종료 시킴. true 는 강제 종료
//        helloFuture.cancel(false); // false 는 수행 끝날 때까지 둠. isDone() 은 바로 찍히지만 Thread 는 돌고 있음
                                                     // true, false 뭐가 되었건 cancel 하면 get 은 불가 (오류 발생), isDone() 도 무조건 true 가 됨 (작업 종료)

        String s = helloFuture.get();   // get() 전에는 쭉 소스대로 실행되지만, get() 을 만나면 결과값 받아오기 전까지 멈춤 (Blocking Call)
        System.out.println(s);

        System.out.println("helloFuture.isDone()?  " + helloFuture.isDone());       // Thread 상태 검사
        System.out.println("End!");

      // 예제 2. 여러 개의 Callable 호출하여 한번에 Future 값 가져오기 (invokeAll, invokeAny)
        System.out.println("\n예제 2. 여러 개의 Callable 호출하여 한번에 Future 값 가져오기 (invokeAll, invokeAny)");

        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(hello, java, world));
        // invokeAll 은 호출한 Callable 이 다 종료될 때 까지 기다렸다가 값을 한번에 받아옴 (ex. hello, world 가 끝나도 가장 오래 걸리는 java 끝날 때까지 기다림)

        System.out.println("- Invoke All");
        for(Future<String> f : futures){
            System.out.println(f.get());
        }

        String str = executorService.invokeAny(Arrays.asList(hello, java, world));
        // InvokeAny 는 호출한 Callable 중 가장 빨리 끝난 것 Return 하고 종료

        System.out.println("- Invoke Any");
        System.out.println(str);

        executorService.shutdown();

    }
}
