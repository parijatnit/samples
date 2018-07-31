package com.solution;

public class LookAndSaySolution {

    public static void main(String[] args) {
        LookAndSaySolution solution = new LookAndSaySolution();
        solution.lookAndSay("1");
    }


    private int count = 0;

    public void lookAndSay(String s) {
        if(count > 5) {
            return;
        }
        char last = '\0';
        int j = 1;
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == last) {
                j++;
            }else if(s.length() > 1 && last != '\0'){
                output.append(Character.forDigit(j, 10));
                output.append(last);
                j = 1;
            }
            last = c;

            if(i == s.length() - 1) {
                output.append(Character.forDigit(j, 10));
                output.append(c);
            }
        }
        System.out.println(output.toString());
        count++;
        lookAndSay(output.toString());
    }



}
