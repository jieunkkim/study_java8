package study.java8to11.completablefuture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorsExample {
    public void testExecutors() throws InterruptedException {
        /**
         * Executor (or ExecutorService)
         *
         * 처리하고자 하는 Thread 가 많아질 수록 예전 방식으로 Thread 를 관리하기 어렵다
         * -> Concurrency 프로그래밍을 위해 Executors 또는 이를 상속받은 Class 들을 활용
         *
         * 1. 고수준 (High-Level) Concurrency 프로그래밍
         *   . 쓰레드를 만들고 관리하는 작업을 애플리케이션에서 분리 -> 이 역할을 Executors 에게 위임.
         * 2. Executors 가 하는 일
         *   . Thread 생성 (App 이 사용할 Thread Pool 을 만들어 관리)
         *   . Thread 생명 주기를 관리
         *   . Thread 작업 처리 및 실행 (Thread 로 실행할 작업을 제공할 수 있는 API 제공)
         * 3. 주요 인터페이스
         *   . Executor : execute(Runnable)
         *   . ExecutorService : Executor 상속 받은 인터페이스로, Callable 도 실행할 수 있으며,
         *                       Executor 를 종료 시키거나, 여러 Callable 을 동시에 실행하는 등의 기능을 제공
         *   . ScheduledExecutorService: ExecutorService 를 상속 받은 인터페이스로 특정 시간
         *                               이후에 또는 주기적으로 작업을 실행할 수 있다.
         */

        /*
            ExecutorService
            - 반드시 shutdown() 을 해줘야 한다.
              . Shutdown 을 직접 해주기 전까지는 계속 대기 상태로 남아 있다.
            newSingleThreadExecutor() -> Thread 를 하나만 띄우는 것

         */
        ExecutorService executorService = Executors.newSingleThreadExecutor();  // Thread 를 하나만 쓰는 Executor
        executorService.execute(() -> {     // Runnable 의 run()
                System.out.println("SingleThread execute() / Thread " + Thread.currentThread().getName());
        });

        executorService.submit(() -> {      // Runnable 의 run()
                System.out.println("SingleThread submit() / Thread " + Thread.currentThread().getName());
        });


        ExecutorService executorService2 = Executors.newFixedThreadPool(2);  // 2개의 Thread 로 submit 을 수행
        executorService2.submit(getRunnable("One"));
        executorService2.submit(getRunnable("Two"));
        executorService2.submit(getRunnable("Three"));
        executorService2.submit(getRunnable("Four"));


        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.schedule(getRunnable("Sch One"), 3, TimeUnit.SECONDS);     // delay 3초로 Schedule 정해서 수행시키기
        scheduledExecutorService.schedule(getRunnable("Sch Two"), 1, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(getRunnable("Fixed Rate"), 4, 1, TimeUnit.SECONDS); // period 만큼의 interval 을 가지고 계속 Thread 실행


        Thread.sleep(10000L);
        executorService.shutdown();         // graceful shutdown 방식 - 현재 진행 중인 작업은 끝까지 마치고 종료시킨다.
        //executorService.shutdownNow();      // 일반 shutdown 과 다르게 바로 종료시킨다.

        executorService2.shutdown();
        scheduledExecutorService.shutdown();

    }

    private Runnable getRunnable(String s) {
        return () -> System.out.println("FixedThreadPool submit() / " + s +" / Thread " + Thread.currentThread().getName());
    }
}
