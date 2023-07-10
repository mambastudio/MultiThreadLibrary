/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.forkjoin;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author user
 */
public class TestForkJoin {
    public static void main(String... args)
    {      
        Random random = new Random();

        List<Long> data = random
            .longs(10_000_000, 1, 100)
            .boxed()
            .collect(toList());
        
        testSequentially(data);
        testForkJoin(data);
        
        
    }
    
    private static void testForkJoin(List<Long> data) {
        final long start = System.currentTimeMillis();

        ForkJoinPool pool = new ForkJoinPool();
        SumTask task = new SumTask(data);
        pool.invoke(task);

        System.out.println("Executed with fork/join in (ms): " + (System.currentTimeMillis() - start));
    }

    private static void testSequentially(List<Long> data) {
        final long start = System.currentTimeMillis();

        long sum = 0;
        for (Long l: data) {
          sum += l;
        }

        System.out.println("Executed sequentially in (ms): " + (System.currentTimeMillis() - start));
    }
}
