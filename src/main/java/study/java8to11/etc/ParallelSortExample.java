package study.java8to11.etc;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class ParallelSortExample {
    public static void testParallelSort() {
        /**
         * Arrays.parallelSort()
         *
         * Fork/Join 프레임워크를 사용해서 배열을 병렬로 정렬하는 기능을 제공한다.
         *
         * - 병렬 정렬 알고리즘
         *   . 배열을 둘로 계속 쪼갠다.
         *   . 합치면서 정렬한다. (Merge Sort 비슷하게 수행하는 듯)
         *   . 시간 O(n logN) 공간 O(n)
         */

        int size = 1500;
        int[] numbers = new int[size];
        Random random = new Random();

        // numbers 에 random 값 insert
        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        long start = System.nanoTime();

        Arrays.sort(numbers);   // 일반 Sort - Single Thread 사용
        System.out.println("serial sorting took " + (System.nanoTime() - start));   // 일반 Sort 시간 출력

        // numbers 에 random 값 다시 insert
        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        start = System.nanoTime();

        Arrays.parallelSort(numbers);   // 병렬 Sort - ForkJoinThread 를 사용하여 Multi Thread 로 병렬 Sort 수행
        System.out.println("parallel sorting took " + (System.nanoTime() - start));     // 병렬 Sort 시간 출력
    }
}
