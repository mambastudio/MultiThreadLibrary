/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread.model;

import java.util.HashMap;

/**
 *
 * @author user
 */
public class ThreadPoolFactory {
    private static final HashMap<String, ThreadPool> poolCategory = new HashMap();
    
    public static void register(String poolName)
    {
        if(!poolCategory.containsKey(poolName))
            poolCategory.put(poolName, new ThreadPool());
    }
    
    public static void invoke(String poolName, Runnable runnable)
    {
        register(poolName);
        poolCategory.get(poolName).execute(runnable);
    }
}
