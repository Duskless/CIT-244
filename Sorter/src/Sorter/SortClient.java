/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sorter;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author mfaux02
 */
public class SortClient {
    public static void main(String[] args) {
        RadixSorter r = new RadixSorter();
        BubbleSorter b = new BubbleSorter();
        
        
        int[] unsorted = getArray(1000000);
        int[] sorted = r.radixSort(unsorted);
        
        System.out.println(Arrays.toString(unsorted));
        System.out.println(Arrays.toString(sorted));
        
        
        
        System.out.println(checkSort(sorted));
        
        long[] time = r.getRuntime();
        
        System.out.println(time[1] - time[0]);
        
//        sorted = b.bubbleSort(unsorted);
//        
//        System.out.println(checkSort(sorted));
//        
//        time = b.getRuntime();
//        
//        System.out.println(time[1] - time[0]);
    }
    
    public static boolean checkSort(int[] sorted){
        boolean sort = true;
        for(int i = 0; i < sorted.length - 1; i++){
            if(sorted[i] <= sorted[i+1]){
                
            }else{
                sort = false;
            }
        }
        
        return sort;
    }
    
    public static int[] getArray(int size){
        Random rng = new Random();
        
        
        
        int[] a = new int[size];
        int r;
        
        for(int i = 0; i < size; i++){
            r = rng.nextInt();
            if(r < 0){
                r = r * -1;
            }
            a[i] = r;
        }
        
        return a;
    }
}
