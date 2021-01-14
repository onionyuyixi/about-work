package com.example.aboutwork.queue;

import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

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
    //up down的區別在於 up在循環改變parent index  down在修改child index
    // offer（添加元素）----> up   poll(移除元素)---heapify--->down
    private static <T> void siftUpComparable(int k, T x, Object[] array) {
        Comparable<? super T> key = (Comparable<? super T>) x;
        while (k > 0) {
            int parent = (k - 1) >>> 1;  //子節點的位置 反求父節點 無論是（left 2n+1 還是 2n+2 ）此方法的結果都是n
            Object e = array[parent];
            if (key.compareTo((T) e) >= 0) //子節點不小於父節點 乃其常則
                break;
            array[k] = e;
            k = parent;
        }
        array[k] = key;
    }

//
//    private void removeAt(int i) {
//        Object[] array = queue;
//        int n = size - 1;
//        if (n == i) // removed last element
//            array[i] = null;
//        else {
//            E moved = (E) array[n];
//            array[n] = null;
//            Comparator<? super E> cmp = comparator;
//            if (cmp == null)
                  // i 元素在數組中的index moved原數組的最后的一個元素 array修改后的數組 最後一元素是null n 原數組的大小
//                siftDownComparable(i, moved, array, n);
//            else
//                siftDownUsingComparator(i, moved, array, n, cmp);
//            if (array[i] == moved) { 如果排序偶然遇到值相等的情況 則修改父節點
//                if (cmp == null)
//                    siftUpComparable(i, moved, array);
//                else
//                    siftUpUsingComparator(i, moved, array, cmp);
//            }
//        }
//        size = n;
//    }


    public static void main(String[] args) {
        Integer[] integers = {3, 2, 1, 8, 9, 4, 6};
//        siftDownComparable(0,3,integers,integers.length);

        int n = integers.length-1;
        Object moved =  integers[n];
        integers[n] = null; //將數組的最後一元素變爲null
        siftDownComparable(2,moved,integers,n);//然後將倒數第二個元素 進行排序后 覆蓋點要刪除的元素
        for (Integer integer : integers) {
            System.err.println(integer);
        }

    }




}
