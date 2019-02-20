package com.algorithm.algorithm.sort;

/**
 * 插入排序算法
 * @author zhangjie
 * @date 2019/2/18 15:23
 */
public class InsertionSortAlgorithm {

    /**
     *      插入排序（Insertion-Sort）的算法描述是一种简单直观的排序算法。它的工作原理是通过构建有序序列，对于未排序数据，
     * 在已排序序列中从后向前扫描，找到相应位置并插入。插入排序在实现上，通常采用in-place排序（即只需用到O(1)的额外
     * 空间的排序），因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。
     *
     * 算法描述
     *      一般来说，插入排序都采用in-place在数组上实现。具体算法描述如下：
     *     1 从第一个元素开始，该元素可以认为已经被排序；
     *     2 取出下一个元素，在已经排序的元素序列中从后向前扫描；
     *     3 如果该元素（已排序）大于新元素，将该元素移到下一位置；
     *     4 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
     *     5 将新元素插入到该位置后；
     *     重复步骤2~5。
     *
     * 算法分析
     *     最佳情况：T(n) = O(n)   最坏情况：T(n) = O(n2)   平均情况：T(n) = O(n2)
     */
    public static int[] insertionSort(int[] arr) {
        if(arr == null || arr.length <= 1){
            return arr;
        }
        int currentValue;
        //因为第一次不用，所以从1开始
        for(int i = 1; i <arr.length; i++){
            //要插入的数
            currentValue = arr[i];
            int j = i - 1;
            //从后往前循环，将大于currentValue的数向后移动一位
            while (j >= 0 && arr[j] > currentValue ){
                arr[j+1] = arr[j];
                j--;
            }
            //找到位置，插入当前元素
            arr[j+1] = currentValue;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {3,15,9,20,11,16,10,15,7,2};
        for(int a : arr){
            System.out.print(a+"\t");
        }
        System.out.println();
        int[] arrSort = insertionSort(arr);
        for(int a : arrSort){
            System.out.print(a+"\t");
        }
    }

}
