package com.maomao.leetcode;

import java.util.HashSet;
import java.util.Set;

public class Question3 {
    public static void main(String[] args) {
        String s = "qwertasdddasdfasf";
        System.out.println(lengthOfLongestSubstring1(s));
    }

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j <= n; j++)
                if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
        return ans;
    }

    public static boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) return false;
            set.add(ch);
        }
        return true;
    }

    public static int lengthOfLongestSubstring1(String s) {
        int ll = s.length();
        int i = 0, j = 0, ans = 0;
        Set<Character> set = new HashSet<>();
        while (i < ll && j < ll) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                set.clear();
                i = j;
            }
        }
        return ans;
    }
}
