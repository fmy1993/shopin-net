// ========================================================================
// Copyright (c) 2009-2009 Mort Bay Consulting Pty. Ltd.
// ------------------------------------------------------------------------
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// and Apache License v2.0 which accompanies this distribution.
// The Eclipse Public License is available at 
// http://www.eclipse.org/legal/epl-v10.html
// The Apache License v2.0 is available at
// http://www.opensource.org/licenses/apache2.0.php
// You may elect to redistribute this code under either of these licenses. 
// ========================================================================


package net.shopin.Algorithm;

import java.util.Random;


/* ------------------------------------------------------------ */
/**
 *  说明:
 *      排序算法总结 
 *      created by kongm 
 */
public class SortAlgorithm
{
    /**
     * 说明:
     *     生成测试随机数
     */
    public static int[] createArray(){
        Random random = new Random();
        int array[] = new int[10];
        for(int i = 0;i < array.length; i ++){
            array[i] = random.nextInt(100) - random.nextInt(100);
        }
        System.out.println("======原始序列=====");
        printArray(array);
        return array;
    }
    
    /**
     * 说明:
     *     打印随机数
     */
    public static void printArray(int[] array){
        for( int i = 0;i < array.length ; i ++){
            System.out.print(array[i] + ",");
        }
        System.out.println();
    }
    /**
     * 说明:
     *      交换俩个数
     */
    public static void swap(int[] data,int x , int y){
        int temp = data[x];
        data[x] = data[y];
        data[y] = temp;
    }
    
    
    
    /**
     * 1.冒泡排序 第一种实现
     */
    public static void bubbleSort(int[] array){
        int temp;
        for(int i = 0;i<array.length;i++){
            //外层循环控制趟数 第几趟  0 n-1  1 n-1-1 2 n-2-1 ... 
            for(int j = 0;j < array.length - i - 1;j ++){
                //内层循环控制比较次数 第1次需要比较 n-1 次,第2次需要n-2次 以此递减
                if(array[j] > array[j + 1]){
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
    
    /**
     * 2.冒泡排序的第二种实现
     * 
     */
    public static void bubbleSort2(int[] array){
        int in,out;
        for(out = array.length - 1;out >= 1;out --){//out loop backward 控制最右边的游标
            for(in = 0;in < out;in ++){//inner loop forward 控制左边无序区
                if(array[in] > array[in + 1]){
                    swap(array,in,in + 1);//swap 
                }
            }
        }
        
    }
    
    
    
    
    
    /**
     * 说明:
     *      直接选择排序
     */
    public static void selectSort(int[] data){
        for(int i = 0; i < data.length;i++){//倘数
            int min = i;
            for( int j = i; j < data.length;j ++){
                if(data[min] > data[j])
                    min = j;
            }
            if(min!=i){
                swap(data,i,min);
            }
           
        }
        printArray(data);
    }
    
    /**
     * 选择排序的第二种实现
     * 选择排序的不变性: out左边的总是有序的
     */
    public static void selectSort2(int[] array){
        int out,in,min;
        for(out = 0;out < array.length - 1;out ++){//outer loop 游标在最左边开始
            min = out;//min默认始终在左边
            for(in = out + 1; in < array.length - 1;in ++){//inner loop 
                if(array[in] < array[min]){
                    min = in;//交换index
                }
            }
            swap(array,out,min);
        }
    }
    
    
    /**
     * 说明:
     *     插入排序 
     */
    public static void insertSort(int[] data){
        for(int i = 1;i < data.length;i ++){
            for(int j = 0; j < i; j++){
                //升序排列
                if(data[j] > data[i])
                    swap(data,j,i);
            }
            
        }
        printArray(data);
    }
    
    /**
     * 插入排序的第二种实现
     */
    public static void insertSort2(int[] array){
        int in,out;
        for(out = 1;out < array.length - 1;out ++){//从左边第二个开始 divide ranges 
            int temp = array[out];//腾出位置
            in = out;//从当前位置开始向左找到正确位置插入
            while(in > 0&& array[in - 1] > temp){
                array[in] = array[in - 1];//比要插入的数大的右移
                --in;
            }
            array[in] = temp;//插入带排序的数
        }
        
    }
    
    
    
    
    public static void quickSort(int[] array){
        if(array.length > 0){
            quickSort(array,0,array.length - 1);
        }
    }
    
    public static void quickSort(int[] array , int start ,int end ){
        if( start < end ){
            int middle = getMiddle(array , start , end);
            quickSort(array,start,middle - 1);
            quickSort(array,middle + 1, end);
        }
        printArray(array);
    }
    
    public static int getMiddle(int[] array, int start , int end){
        int pivot = array[start];//中轴默认为第一个
        while(start < end ){
            while(start < end && array[end] <= pivot){
                end --;
            }
                array[start] = array[end];
            while(start < end && array[start] >= pivot){
                start ++;
            }
            array[end] = array[start];
        }
        array[start] = pivot;
        return start;
    }
    

    /* ------------------------------------------------------------ */
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        //1.冒泡排序1的调用
        int array[] = {11,2,44,13,55,33,22};
        bubbleSort(array);
        for(int i = 0;i < array.length;i ++){
            System.out.print(array[i] + ",");
        }
        //测试随机数
        int result[] = createArray();
        System.out.println("直接选择排序");
        selectSort(result);
        System.out.println("插入排序");
        insertSort(result);
        System.out.println("快速排序");
        quickSort(result);
        printArray(result);
        
        
        System.out.println("冒泡排序第二种实现");
        bubbleSort2(result);
        printArray(result);
        
        System.out.println("选择排序的第二种实现");
        selectSort2(result);
        printArray(result);
        
        System.out.println("插入排序的第二种实现");
        insertSort2(result);
        printArray(result);
        
        
    }

}
