/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.logging.Level;
import java.util.logging.Logger;
import thread.model.LambdaThread;

/**
 *
 * @author user
 */
public class Test2 {
    public static void main(String... args)
    {
        LambdaThread thread = new LambdaThread();
        System.out.println(thread.isTerminated());
        thread.startExecution(()-> {
            
        });
        thread.stopExecution();
        System.out.println(thread.isTerminated());
    }
}
