/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sorter;

/**
 *
 * @author mfaux02
 */
public class BubbleSorter {
    
    private long[] runtime;
    
    BubbleSorter(){
        this.runtime = new long[2];
    }
    
    public int[] bubbleSort(int[] arrToSort){
        runtime[0] = java.time.Instant.now().toEpochMilli();
        
        int[] rtn = sort(arrToSort);
        
        runtime[1] = java.time.Instant.now().toEpochMilli();
        
        return rtn;
    }
    
    public long[] getRuntime(){
        return runtime;
    }
    
    private int[] sort(int[] input){
        int swap;
        int run = 0;
        
        while(run < input.length - 1){
            run = 0;
            for(int i = 0; i < input.length - 1; i++){
                if(input[i] > input[i + 1]){
                    swap = input[i];
                    input[i] = input[i + 1];
                    input[i + 1] = swap;
                }else{
                    run++;
                }
            }
        }
        
        return input;
        
    }
}
