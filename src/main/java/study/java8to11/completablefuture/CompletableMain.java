package study.java8to11.completablefuture;

public class CompletableMain {
    public void completableMain() throws InterruptedException {
        ConcurrentExample concurrentExample = new ConcurrentExample();
        ExecutorsExample executorsExample = new ExecutorsExample();

        //concurrentExample.testConcurrent();
        executorsExample.testExecutors();
    }
}
