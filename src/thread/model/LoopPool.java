/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread.model;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author user
 */
public class LoopPool {
    boolean suspend = false;    
    boolean finish  = false;
    private final int poolSize;
    
    private final List<LoopOnce> pool;    
    private final LambdaThread managerPool;
    
    public LoopPool()
    {
        poolSize = Runtime.getRuntime().availableProcessors();
        pool = Arrays.asList(new LoopOnce[poolSize]);
        for(int i = 0; i<poolSize; i++)
            pool.set(i, new LoopOnce());
            
        managerPool = new LambdaThread();
    }
    
    public void execute(Consumer<LoopOnce> call)
    {
        managerPool.startExecution(()->{
            for(LoopOnce thread : pool)
                thread.startExecution(call);
            for(LoopOnce thread : pool)            
                thread.join();           
        });        
    }
    
    public void execute(Consumer<LoopOnce> call, Runnable overall)
    {
        managerPool.startExecution(()->{
            for(LoopOnce thread : pool)
                thread.startExecution(call);
            for(LoopOnce thread : pool)            
                thread.join();     
            overall.run();
        });        
    }
    
    public void pause()
    {
        for(LoopOnce thread : pool)
            thread.pauseExecution();
    }
    
    public void resume()
    {
        for(LoopOnce thread : pool)
            thread.resumeExecution();
    }
    
    public void stop()
    {
        for(LoopOnce thread : pool)
            thread.stopExecution();
        managerPool.stopExecution();
    }
}
