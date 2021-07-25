package study.java8to11.completablefuture;

public class ConcurrentExample {
    public void testConcurrent() throws InterruptedException {

      // 예제 1. Thread 구현하는 방법
        System.out.println("예제 1. Thread 구현하는 방법");
        // 방법 1. Thread Class 를 상속받아 구현
        MyThread oldThread = new MyThread();

        // 방법 2. anonymous class 를 가져와 Runnable 영역 구현 후 사용
        Thread newThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread2 : " + Thread.currentThread().getName());
            }
        });

        // 방법 3. 방법 2를 Lambda 구 구현한 것
        Thread lambdaThread = new Thread(() ->
                System.out.println("Thread3 : " + Thread.currentThread().getName()));

      // 예제 2. Thread 관련 수행들
        Thread sleepThread = new Thread(() ->{
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();    // 자는 동안 누군가가 이 Thread 를 깨우면 발생하는 Exception
            }
            System.out.println("\n예제 2. Thread 관련 수행들 (ex. sleep)");
            System.out.println("First Thread : " + Thread.currentThread().getName());
        });


        sleepThread.start();        // sleepTread 가 가장 먼저 호출되지만, sleep 되어 있는 동안 다른 Thread 우선 처리가 가능하여 가장 마지막에 출력됨

        oldThread.start();
        newThread.start();
        lambdaThread.start();
        System.out.println("Thread4 : " + Thread.currentThread().getName());
        /* 예제 1 출력 결과 -> 매번 순서 다름 Thread 는 순서 보장을 못한다. 코드 순서상 thread start()가 먼저 실행되어도, 실제 작동은 순서가 섞일 수 있다.
            Thread2 : Thread-1
            Thread1 : Thread-0
            Thread4 : main
            Thread3 : Thread-2

          예제 2 까지 포함된 출력 결과 -> 예제 2가 먼저 호출되지만, sleep 하는 동안 다른 Thread 들이 우선 처리되어 예제 2는 예제 1 뒤에 출력
            예제 1. Thread 구현하는 방법
            Thread3 : Thread-2
            Thread4 : main
            Thread1 : Thread-0
            Thread2 : Thread-1

            예제 2. Thread 관련 수행들 (ex. sleep)
            First Thread : Thread-3
            */

       Thread.sleep(1000L);

      // 예제 3. Thread Interrupt 해보기 (아래 예제의 경우 sleep 상태의 Thread 를 interrupt() 가 깨운 것)
        Thread loopThread = new Thread(() ->{
            System.out.println("\n예제 3. Thread Interrupt 해보기  ");
            while(true) {
               System.out.println("Thread : " + Thread.currentThread().getName());
               try {
                   Thread.sleep(1000L);
               } catch (InterruptedException e) {
                   System.out.println("exit!");
                   return;
               }
           }
        });

        loopThread.start();
        Thread.sleep(3000L);
        loopThread.interrupt();

        Thread.sleep(1000L);

      // 예제 4. Thread Join 해보기 (join() 은 해당 Thread 가 끝날 때 까지 기다려줘야 할 때 사용)
        Thread joinThread = new Thread(() ->{
            System.out.println("\n예제 4. Thread Join 해보기 (join() 은 해당 Thread 가 끝날 때 까지 기다려줘야 할 때 사용)");
            System.out.println("Thread : " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new IllegalStateException();
            }
        });

        joinThread.start();
        joinThread.join();
        System.out.println(joinThread + " is finished");

    }

    // 방법 1. Thread 를 상속받아 구현하기
    class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread1 : " + Thread.currentThread().getName());
        }
    }
}
