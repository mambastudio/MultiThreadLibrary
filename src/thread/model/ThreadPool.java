/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread.model;

import java.util.Stack;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 * 
 * Simple thread pool implementation. Java ExecutorService is just a thread pool
 * implementation hence no special additional methods are required. This class 
 * therefore is for verbosity purpose. 
 * 
 */
public class ThreadPool {
    private final ThreadPoolExecutor pool;
    private final int poolSize;
    private final Stack<Future> futures;
    
    public ThreadPool()
    {        
        poolSize = Runtime.getRuntime().availableProcessors();
        pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(poolSize);
        futures = new Stack<>();
    }
    
    public void execute(Runnable runnable)
    {
        Future future = pool.submit(runnable);
        futures.push(future);
    }
    
    public void waitAndShutdown()
    {
        while(true)
        {
            if(!futures.isEmpty())
            {
                Future future = futures.pop();
                try {
                    future.get();                    
                } catch (InterruptedException | ExecutionException ex) {
                    Logger.getLogger(ThreadPool.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                shutdown();
                return;
            }
        }        
    }
       
    public void shutdown()
    {        
        pool.shutdown();
    }
}
