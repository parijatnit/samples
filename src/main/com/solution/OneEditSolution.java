package com.solution;

public class OneEditSolution {

    public static void main(String[] args) {

        String word1 = "abcd";
        String word2 = "abcde";

        OneEditSolution solution = new OneEditSolution();
        boolean result = solution.oneEditSolution(word1, word2);

        System.out.println("result is: " + result);

        System.out.println("digit is: " + Character.forDigit(9, 10));
    }



    private int count = 0;

    public boolean oneEditSolution(String w1, String w2) {
        if(count > 1) {
            return false;
        }
        if(w1.length() == 0 && w2.length() == 0) {
            return (count == 1);
        }

        boolean matched = false;
        if(w1.length() == 0 || w2.length() == 0 || w1.charAt(0) != w2.charAt(0)) {
            count++;
        } else {
            matched = true;
        }

        String nw1 = (w1.length() == 0 || w1.length() < w2.length() && !matched) ? w1 : w1.substring(1, w1.length());
        String nw2 = w2.length() == 0 || w2.length() < w1.length() && !matched ? w2 : w2.substring(1, w2.length());

        return oneEditSolution(nw1, nw2);
    }




}
