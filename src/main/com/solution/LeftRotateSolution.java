package com.solution;

public class LeftRotateSolution {

    public static void main(String[] args) {
        LeftRotateSolution solution = new LeftRotateSolution();
        String s = "a";
        int d = 2;
        String rotated = solution.leftRotate(s, d);
        System.out.println(rotated);
    }


    public String leftRotate(String s, int d) {
        if(s.length() == 0 || s.length() == 1) {
            return s;
        }

        char[] chars = s.toCharArray();
        int rc = 0;
        while(rc <d) {
            char temp = chars[0];
            System.arraycopy(chars, 1, chars, 0, chars.length - 1);
            chars[chars.length - 1] = temp;
            rc++;
        }

        return new String(chars);
    }



}
