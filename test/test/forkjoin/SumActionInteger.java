/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.forkjoin;

import java.util.concurrent.RecursiveAction;

/**
 *
 * @author user
 */
public class SumActionInteger extends RecursiveAction{
    int start, end;
    int[] array;
    
    public SumActionInteger(int[] array, int start, int end)
    {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
