package com.practice;

import java.util.*;

public class BracketPattern {

    static final Map<Character, Character> complementMap = buildComplementMap();

    public static Map<Character, Character> buildComplementMap() {
        Map<Character, Character> complementMap  = new HashMap<>();
        complementMap.put('[',']');
        complementMap.put('{','}');
        complementMap.put('(',')');
        return complementMap;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            String s = in.next();
            String valid = isValidPattern(s) ? "YES" : "NO";
            System.out.println(valid);
        }
    }


    public static boolean isValidPattern(String pattern) {
        Deque<Character> stack = new ArrayDeque<>(pattern.length());
        char lastOpenedBracket = '\0';
        char complement = '\0';
        for(char bracket : pattern.toCharArray()) {
            switch(bracket) {
                case '[':
                case '{':
                case '(':
                    stack.addFirst(bracket);
                    lastOpenedBracket = bracket;
                    break;
                case ']':
                case '}':
                case ')':
                    complement = complementMap.get(bracket);
                    if(lastOpenedBracket == complement) {
                        stack.removeFirst();
                        lastOpenedBracket = stack.peekFirst();
                    } else {
                        return false;
                    }
                default:
                    break;
            }
        }
        return stack.isEmpty();
    }
}
