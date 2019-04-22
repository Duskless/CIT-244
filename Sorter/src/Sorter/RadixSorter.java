/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sorter;

import java.util.LinkedList;

/**
 *
 * @author mfaux02
 */
public class RadixSorter {
    final String sortAlgorithm = "Radix Sort";
    
    private long[] runtime;
    
    RadixSorter(){
        this.runtime = new long[2];
    }
    
    public long[] getRuntime(){
        return runtime;
    }
    
    /**
     * 
     * @param arrToSort
     * @return 
     */
    public int[] radixSort(int[] arrToSort){
        
        runtime[0] = java.time.Instant.now().toEpochMilli();
        
        int size = getDigits(arrToSort);
        int length = 1;
        
        while(size > 1){
            length = length * 10;
            size--;
        }
        
        int[] rtn = sortInt(arrToSort, length);
        
        runtime[1] = java.time.Instant.now().toEpochMilli();
        return rtn;
    }
    
    private int[] sortInt(int[] arrToSort, int modulus){
        LinkedList<Integer>[] list = new LinkedList[10];
        
        for(int i = 0; i < 10; i++){
            list[i] = new LinkedList();
        }
        
        for(int i = 0; i < arrToSort.length; i++){
            int index = (arrToSort[i] / modulus)%10;
            
            if(index >= 0 && index < 10){
                list[index].add(arrToSort[i]);
            }else{
                System.out.println("Out of bounds! " + index + ":" + arrToSort[i]);
            }
        }
        
        return sort(list,modulus);
    }
    
    private int[] sortInteger(Integer[] arrToSort, int modulus){
        LinkedList<Integer>[] list = new LinkedList[10];
        
        for(int i = 0; i < 10; i++){
            list[i] = new LinkedList();
        }
        
        for(int i = 0; i < arrToSort.length; i++){
            int index = (arrToSort[i] / modulus)%10;
            
            if(index >= 0 && index < 10){
                list[index].add(arrToSort[i]);
            }else{
                System.out.println("Out of bounds! " + index + ":" + arrToSort[i]);
            }
        }
        
        return sort(list,modulus);
    }
    
    private int[] sort(LinkedList<Integer>[] list, int modulus){
        
        int[][] sort = new int[10][0];
        
        
        for(int i = 0; i < 10; i ++){
            if(!list[i].isEmpty()){
                Integer[] a = new Integer[1];
                a = list[i].toArray(a);

                if(modulus > 1){
                    sort[i] = sortInteger(a,modulus/10);
                }else{
                    sort[i] = new int[a.length];
                    for(int b = 0; b < a.length; b++){
                        sort[i][b] = a[b];
                    }
                }
            }
        }
        
//        for(int i = 0; i < 10; i++){
//            System.out.println(Arrays.toString(sort[i]));
//        }
        
        int[] rtn = new int[sort[0].length + 
                sort[1].length  + 
                sort[2].length  + 
                sort[3].length  + 
                sort[4].length  + 
                sort[5].length  + 
                sort[6].length  + 
                sort[7].length  + 
                sort[8].length  + 
                sort[9].length  ];
        
        int count = 0;
        
        for(int i = 0; i < 10; i++){
            if(sort[i] != null){
                for(int a = 0; a < sort[i].length; a++){
                    rtn[count] = sort[i][a];
                    count++;
                }   
            }
        }
        
        return rtn;
    }
    
    private int getDigits(int[] arrToSort){
        int a = 0;
        
        for(int i = 0; i < arrToSort.length; i++){
            if(arrToSort[i] > a){
                a = arrToSort[i];
            }
        }
        boolean run = true;
        int r = 0;
        
        while(run){
            a = a / 10;
            if(a >= 1){
                r++;
            }else{
                r++;
                run = false;
            }
        }
        
        return r;
    }
}
