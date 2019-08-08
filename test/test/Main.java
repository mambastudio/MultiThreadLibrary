package test;

import java.util.concurrent.TimeUnit;
import thread.model.TimerExecution;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Main {
    static int count = 0;
    public static void main(String... args)
    {
        TimerExecution timer = new TimerExecution(0, 5, TimeUnit.SECONDS);
        timer.execute(()-> {
            System.out.println("kubafu   " +count);
            count++;
                });
    }
}
