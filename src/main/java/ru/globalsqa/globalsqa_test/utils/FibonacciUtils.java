package ru.globalsqa.globalsqa_test.utils;

import java.time.LocalDate;

public class FibonacciUtils {

    public static int result;

    public static void calculateFibonacci() {
        LocalDate today = LocalDate.now();
        int n = today.getDayOfMonth() + 1;
        result = fibonacci(n);
    }

    private static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
