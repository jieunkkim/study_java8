package study.java8to11.completablefuture;

public class CompletableMain {
    public void completableMain() throws InterruptedException {
        ConcurrentExample concurrentExample = new ConcurrentExample();
        concurrentExample.testConcurrent();
    }
}
