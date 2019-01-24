package com.maomao.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Question1 {
    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 3, 5, 6, 9, 8};
        int target = 8;
        int[] aa = twosum2(nums, target);
        for (int a : aa) {
            System.out.println(a);
        }
    }

    public static int[] twosum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (target == nums[i] + nums[j]) {
                    return new int[]{i,j};
                }
            }
        }
        throw new IllegalArgumentException("无");
    }
    public static int[] twosum1(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for (int i = 0;i<nums.length;i++){
            map.put(nums[i],i);
        }
        for (int i =0;i<nums.length;i++){
            int tmp = target-nums[i];
            if (map.containsKey(tmp)&&map.get(tmp)!= i){
                return new int[]{i,map.get(tmp)};
            }
        }
        throw new IllegalArgumentException("No");
    }
    public static int[] twosum2(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0;i<nums.length;i++){
            int tmp = target-nums[i];
            if (map.containsKey(tmp)){
                return new int[]{i,map.get(tmp)};
            }
            map.put(nums[i],i);
        }
        throw new IllegalArgumentException();
    }
}
