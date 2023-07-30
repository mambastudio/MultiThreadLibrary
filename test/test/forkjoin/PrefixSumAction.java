/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.forkjoin;

import java.util.concurrent.RecursiveAction;

/**
 *
 * @author jmburu
 */
public class PrefixSumAction extends RecursiveAction{
    private final int[] array;
    private final int start;
    private final int end;
    public PrefixSumAction(int[] array, int start, int end)
    {
        this.array = array;
        this.start = start;
        this.end = end;
    }
    @Override
    protected void compute() {
        int n = end - start;
        if(end - start < 2)
        {
            array[end - 1] = array[end - 1] + array[end - 2];
        }
        else
        {
            
        }
    }
    
}
