package com.max.app.hackerrank;

import java.io.IOException;

public final class HighestValuePalindrome {

    public static void main(String[] args) throws IOException {

        // expected: "992299"
        final String str = "092282";
        final int n = str.length();
        final int k = 3;

        final String result = highestValuePalindrome(str, n, k);

        System.out.println(result);

        System.out.printf("java version: %s%n", System.getProperty("java.version"));
    }

    /**
     * Highest Value Palindrome.
     * https://www.hackerrank.com/challenges/richie-rich/problem
     * time: O(N)
     * space: O(N)
     */
    static String highestValuePalindrome(String str, int n, int k) {

        char[] arr = str.toCharArray();

        int notMatchedPos = calculateNotMatchedPositions(arr);

        if (notMatchedPos > k) {
            return "-1";
        }

        int leftEdits = k;
        for (int left = 0, right = arr.length - 1; left <= right; ++left, --right) {

            assert leftEdits >= notMatchedPos;

            // matched characters
            if (arr[left] == arr[right]) {

                // odd length, single character case
                if (left == right) {
                    if (leftEdits > 0 && arr[left] != '9') {
                        arr[left] = '9';
                        leftEdits -= 1;
                    }
                }
                else if ((leftEdits - 2) >= notMatchedPos && arr[left] != '9') {
                    arr[left] = '9';
                    arr[right] = '9';
                    leftEdits -= 2;
                }
            }
            // not matched characters
            else {
                if (leftEdits > notMatchedPos) {
                    if (arr[left] != '9') {
                        arr[left] = '9';
                        leftEdits -= 1;
                    }

                    if (arr[right] != '9') {
                        arr[right] = '9';
                        leftEdits -= 1;
                    }
                }
                else {
                    char maxDigit = maxChar(arr[left], arr[right]);
                    arr[left] = maxDigit;
                    arr[right] = maxDigit;
                    leftEdits -= 1;
                }

                --notMatchedPos;
            }

            assert leftEdits >= notMatchedPos;
        }

        return new String(arr);
    }

    static char maxChar(char ch1, char ch2) {
        return ch1 > ch2 ? ch1 : ch2;
    }

    static int calculateNotMatchedPositions(char[] arr) {
        int unmatchedPositions = 0;
        for (int left = 0, right = arr.length - 1; left < right; ++left, --right) {
            if (arr[left] != arr[right]) {
                ++unmatchedPositions;
            }
        }

        return unmatchedPositions;
    }


}
