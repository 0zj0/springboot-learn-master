package com.algorithm.algorithm.sort;

/**
 * 冒泡排序
 * @author zhangjie
 * @date 2019/2/18 16:02
 */
public class BubbleSortAlgorithm {

    private static int count = 0;

    /***
     *      冒泡排序是一种简单的排序算法。它重复地走访过要排序的数列，一次比较两个元素，如果它们的顺序错误就把它们交换过来。
     *  走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。这个算法的名字由来是因为越小的元素会经
     *  由交换慢慢“浮”到数列的顶端。
     *  算法描述
     *     1 比较相邻的元素。如果第一个比第二个大，就交换它们两个；
     *     2 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
     *     3 针对所有的元素重复以上的步骤，除了最后一个；
     *     重复步骤1~3，直到排序完成。
     *  算法分析
     *      最佳情况：T(n) = O(n)   最差情况：T(n) = O(n2)   平均情况：T(n) = O(n2)
     */
    public static int[] bubbleSort(int[] arr){
        if(arr == null || arr.length <= 1){
            return arr;
        }
        int length = arr.length;
        for(int i = 0 ; i < length; i++ ){
            for(int j = 0 ; j < length - i -1 ; j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    count ++;
                }
            }
        }
        return arr;
    }

    /**
     * 冒泡排序的递归实现：
     * 实现逻辑：冒泡排序的实现原理在于选举，第一轮选举出最大值存放到最后一位，第二轮选举出第二大值存放到倒数第二位
     *     以此类推，直到循环结束。
     *     递归实现在于：只有存在相邻的位置大小顺序不一致，则进行值交换，开始递归；
     *     普通实现：循环最大次数(n-1)+(n-2)+...+0 = n*(n-1)/2; 递归实现最大次数：n*(n-1)/2
     * @param arr
     * @return
     */
    public static int[] bubbleSort_Recursion(int[] arr){
        if(arr == null || arr.length <= 1){
            return arr;
        }
        boolean isReplace = false;
        for(int i= 0; i < arr.length -1; i++){
            if(arr[i] > arr[i+1]){
                int temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
                isReplace = true;
                count ++;
                //开始递归
                //return bubbleSort_Recursion(arr);
            }
        }
        if(isReplace){
            //开始递归
            return bubbleSort_Recursion(arr);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {3,15,9,20,11,16,10,15,7,2};
        //int[] arr = {20,19,18,17,16,15,14,13,12,11};
        for(int a : arr){
            System.out.print(a+"\t");
        }
        System.out.println();
        int[] arrSort = bubbleSort_Recursion(arr);
        for(int a : arrSort){
            System.out.print(a+"\t");
        }
        System.out.println();
        System.out.println(count);
    }
}
