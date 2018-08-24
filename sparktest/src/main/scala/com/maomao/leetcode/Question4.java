package com.maomao.leetcode;

import java.util.Set;
import java.util.TreeSet;

public class Question4 {
    public static void main(String[] args) {
        int[] a = {1,1};
        int[] b = {1,2};
        System.out.println(findMedianSortedArrays(a, b));
        System.out.println(findMedianSortedArrays(a, b));
    }

    static double findMedianSortedArrays(int[] A, int[] B) {
        double result = 0;
        Set<Integer> set = new TreeSet<>();
        for (int tmp : A) set.add(tmp);
        for (int tmp : B) set.add(tmp);
        int size = set.size();
        if (0 == size % 2) {
            int count =size/2;
            int i = 1;
            int before =0,after =0;
            for (Integer ii :set){
                if (i==count){
                    before = ii;
                }
                if (i==count+1){
                    after = ii;
                }
                i++;
            }
            result = (double)(before+after)/2;
        }else{
            int count = size /2 +1;
            int i = 1;
            for (Integer ii :set){
                if (i==count){
                    result = ii;
                }
                i++;
            }
        }
        return result;
    }
}
