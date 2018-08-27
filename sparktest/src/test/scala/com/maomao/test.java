package com.maomao;

import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    public static void main(String[] args) {
        String s = "dasdad";
        System.out.println(s.charAt(0));
        System.out.println(7/2 +1);
        System.out.println(7%2);



        Pattern p = Pattern.compile("a*b");
        Matcher m = p.matcher("aaaaab");
        System.out.println("aaaaab".matches("a*b"));
        boolean b = m.matches();
        System.out.println(b);
        Set<Integer> set = new TreeSet<>();
        set.add(4);
        set.add(12);

    }
}
