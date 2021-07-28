package study.java8to11.completablefuture;

import java.util.concurrent.ExecutionException;

public class CompletableMain {
    public void completableMain() throws InterruptedException, ExecutionException {
        ConcurrentExample concurrentExample = new ConcurrentExample();
        ExecutorsExample executorsExample = new ExecutorsExample();
        CallableExample callableExample = new CallableExample();
        CompletableFutureExp1 completableFuture1 = new CompletableFutureExp1();
        CompletableFutureExp2 completableFutureExp2 = new CompletableFutureExp2();

        //concurrentExample.testConcurrent();
        //executorsExample.testExecutors();
        //callableExample.testCallable();
        //completableFuture1.testCompletableFuture1();
        completableFutureExp2.testCompletableFutureExp2();
    }
}
