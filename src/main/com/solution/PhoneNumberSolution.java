package com.solution;

import java.util.*;

class PhoneNumberSolution {

    private static final Map<Character,String> mapping = populateMapping();

    public static void main(String[] args) {
        PhoneNumberSolution solution = new PhoneNumberSolution();
        List<String> values = solution.letterCombinations("23");

        System.out.println("done");
    }



    public static Map<Character,String> populateMapping() {
        Map<Character, String> mapping = new HashMap<>();

        mapping.put('2', "abc");
        mapping.put('3', "def");
        mapping.put('4', "ghi");
        mapping.put('5', "jkl");
        mapping.put('6', "mno");
        mapping.put('7', "pqrs");
        mapping.put('8', "tuv");
        mapping.put('9', "wxyz");
        return mapping;
    }

    //final Set<String> letterCombinations = new HashSet<>();

    public List<String> letterCombinations(String digits) {
        return recurringLetterCombinations(digits, new ArrayList<>(Collections.singleton("")));
    }


    public List<String> recurringLetterCombinations(String digits, final List<String> runningCombinations) {
        // terminate condition
        if(digits.length() == 0) {
            return runningCombinations;
        }
        char digit = digits.charAt(0);
        List<String> newRunningCombinations = new ArrayList<>();
        for(String combination : runningCombinations) {
            for(char ch : mapping.get(digit).toCharArray()) {
                String newCombination = combination + ch;
                newRunningCombinations.add(newCombination);
            }
        }

        return recurringLetterCombinations(digits.substring(1, digits.length()), newRunningCombinations);
    }
}
