package com.example.tp13913;

import static org.testng.AssertJUnit.assertEquals;

public class test {
    public static void main(String[] args) {
        int result = add(5, 7);
        int a=1;
        int b=1;

        // Assert that the result is as expected
        assert result == 12 : "Error: The result is not as expected!";


        System.out.println("The result is: " + result);
    }

    public static int add(int a, int b) {
        return a + b;
    }
}
