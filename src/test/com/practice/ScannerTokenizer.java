package com.practice;

import java.util.Scanner;

/**
 *
 */
public class ScannerTokenizer {

    public static void main(String[] args) {
        String text = "4231, Java Programming, 1000.00, This is a test";

        Scanner scanner = new Scanner(text).useDelimiter("\\s*,\\s*");
        int checkNumber = scanner.nextInt();
        String description = scanner.next();
        float amount  = scanner.nextFloat();
        String description2 = scanner.next();

        System.out.printf("/***** Tokenizing Text *****/\n\n");
        System.out.printf("String to tokenize: %s\n", text);
        System.out.printf("checkNumber: %d\n", checkNumber);
        System.out.printf("description: %s\n", description);
        System.out.printf("amount: %f\n", amount);
        System.out.printf("description: %s\n", description2);
    }


}
