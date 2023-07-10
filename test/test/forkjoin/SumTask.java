/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.forkjoin;

import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 *
 * @author user
 */
public class SumTask extends RecursiveTask<Long> {
    private static final int SEQUENTIAL_THRESHOLD = 5;

    private List<Long> data;

    public SumTask(List<Long> data) {
        this.data = data;
    }

    // ...

    private long computeSumDirectly() {
        long sum = 0;
        for (Long l: data) {
          sum += l;
        }
        return sum;
    }

    @Override
    protected Long compute() {
        if (data.size() <= SEQUENTIAL_THRESHOLD) { // base case
        long sum = computeSumDirectly(); 
        return sum;
    } else { // recursive case
            // Calcuate new range
            int mid = data.size() / 2;
            SumTask firstSubtask =
                new SumTask(data.subList(0, mid));
            SumTask secondSubtask =
                new SumTask(data.subList(mid, data.size()));

            // queue the first task
            firstSubtask.fork();

            // Return the sum of all subtasks
            return secondSubtask.compute()
                    +
                    firstSubtask.join();
        }
    }
}
