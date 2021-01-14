package com.example.aboutwork.queue;

import com.google.common.collect.Lists;

public class 優先隊列説明 {

//The implementation uses an array-based binary heap   以數組為數據存儲 二叉堆

    //core method
    private static <T> void siftDownComparable(int k, T x, Object[] array,
                                               int n) {
        if (n > 0) {
            Comparable<? super T> toInsert = (Comparable<? super T>) x;
            int half = n >>> 1;           // loop while a non-leaf  只取數組的一半即可進行對比
            // 原因就是 int child = (k << 1) + 1;
            while (k < half) {
                int child = (k << 1) + 1; // assume left child is least
                Object c = array[child];
                int right = child + 1;
                if (right < n &&
                        ((Comparable<? super T>) c).compareTo((T) array[right]) > 0)
                    c = array[child = right];  //去子節點中最小的那一個
                if (toInsert.compareTo((T) c) <= 0)  //要插入的數據不大於子節點 則不需要更換位置
                    break;
                array[k] = c; //否則最小的子節點上浮 且更新插入的新位置 遍歷下去 找到最小值 然後推出循環與toInsert交換
                k = child;
            }
            array[k] = toInsert;
        }
    }

    public static void main(String[] args) {
        Object[] integers = Lists.newArrayList(3, 2, 1, 8, 9, 4, 6).toArray();
        siftDownComparable(0,3,integers,integers.length);
        for (Object integer : integers) {
            System.err.println(integer);
        }
    }




}
