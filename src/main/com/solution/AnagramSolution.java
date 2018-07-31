package com.solution;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class AnagramSolution {

    static int sherlockAndAnagrams(String s) {
        Map<String, Integer> map = new HashMap<>();
        int l = s.length();
        for(int i=0; i < (1<<l); i++) {
            String comb = "";
            for(int j =0; j<l; j++) {
                if((i & (1<<j)) > 0) {
                    comb += s.charAt(j);
                }
            }
            String sc = sortString(comb);
            Integer count = map.get(sc);
            int nc = (count == null) ? 1 : count + 1;
            map.put(sc, nc);
        }
        return countAnagrams(map);
    }

    private static String sortString(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }


    private static int countAnagrams(Map<String, Integer> map) {
        int tc = 0;
        for(Integer count : map.values()) {
            if(count!= null && count>1) {

                tc = tc + count/2;
            }
        }
        return tc;
    }

    public static void main(String[] args) {
        String s = "abba";
        int anagramCount = sherlockAndAnagrams(s);
        System.out.println("anagramCount: " + anagramCount);


        Stack<Character> s1 = new Stack<>();
        System.out.println(s1.pop());
    }
}
