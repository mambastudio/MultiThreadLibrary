/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread.model;

import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 * 
 * Single execution class
 * 
 */
public class LoopOnce {
    //Variables to control state of thread
    boolean suspend = false;    
    boolean finish  = false;
    
    //thread to execute
    Thread thread = null;
                
    //Call this inside method execution to make use of thread states
    public synchronized void chill()
    {
        try
        {
            waitForNotificationToResume();
        }
        catch (InterruptedException ex)
        {
            System.err.println(ex);
        }
    }
    
    private synchronized void waitForNotificationToResume() throws InterruptedException
    {
        while(suspend)
        {           
            wait();
        }         
    }
            
    public boolean isPaused()
    {
        return suspend;
    }
    
    public boolean isStopped()
    {
        return finish;
    }
    
    public void startExecution(Consumer<LoopOnce> execute)
    {
        finish = false; //just in case this is a new thread execution
        
        thread = new Thread(()->{
            execute.accept(this);
        });    
        thread.start();
    }
    
    //ensure it's completed
    public void join()
    {
        try {
            thread.join();
            finish = true;
        } catch (InterruptedException ex) {
            Logger.getLogger(LoopOnce.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void stopExecution()
    {
        suspend = false;
        finish = true;
    }
    
    public void pauseExecution()
    {
        suspend = true;
    }

    public synchronized void resumeExecution()
    {        
        suspend = false;
        notify();
    }  
}
