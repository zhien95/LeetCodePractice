package com.leetcode.practice.solution.math;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class MediumMathSolution {
    /**
     * [Factorial Trailing Zeroes]
     *
     * Calculates the number of trailing zeroes in n! (n factorial).
     * Trailing zeros are created by factors of 10, which come from multiplying 2 and 5.
     * Since there are always more factors of 2 than 5 in a factorial, we only need to count factors of 5.
     *
     * @param n Input number
     * @return Number of trailing zeroes in n!
     */
//https://leetcode.com/problems/factorial-trailing-zeroes/?envType=study-plan-v2&envId=top-interview-150
    public int trailingZeroes(int n) {
        //n factorial: n * n-1 ... 5 * 4 * 3 * 2 * 1
        //calculating N! will lead to overflow -> so find a pattern
        //the tail gains a 0 when there's a 5 * 2 and there's plenty of 2 in n factorial
        int count = 0;

        while (n > 0) {
            n /= 5;
            count += n;
        }

        return count;
    }

    //https://leetcode.com/problems/reverse-integer/
    public int reverse(int x) {
        int result = 0;

        while (x != 0) {
            int digit = x % 10;
            if (result > Integer.MAX_VALUE / 10 || result < Integer.MIN_VALUE / 10) {
                return 0;
            }

            result = result * 10 + digit;
            x /= 10;
        }

        return result;

    }


    // function to convert epoch to date key with 4 hour interval
    public String convertEpochToDateKeyWith4HourInterval(long epochSeconds) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(
                Instant.ofEpochSecond(epochSeconds),
                ZoneOffset.UTC
        );

        int hour = dateTime.getHour();
        int interval = (hour / 4) * 4; // Round down to nearest 4-hour mark

        // Format the date and adjusted hour
        String dateString = String.format("%04d-%02d-%02d %02d",
                dateTime.getYear(),
                dateTime.getMonthValue(),
                dateTime.getDayOfMonth(),
                interval);

        return dateString + ":00:00"; // Return in format "yyyy-MM-dd HH:00:00"
    }

    public static void main(String[] args) {
        MediumMathSolution solution = new MediumMathSolution();

        // Test each 4-hour interval group
        System.out.println("Testing convertEpochToDateKeyWith4HourInterval function:");

        // Test hour 0 (00:xx:xx) should round to 00:00:00
        long epoch1 = 1609459200L; // 2021-01-01 00:00:00 UTC
        System.out.println("Input: " + epoch1 + " -> Output: " + solution.convertEpochToDateKeyWith4HourInterval(epoch1));

        // Test hour 1 (01:xx:xx) should round to 00:00:00
        long epoch2 = 1609462800L; // 2021-01-01 01:00:00 UTC
        System.out.println("Input: " + epoch2 + " -> Output: " + solution.convertEpochToDateKeyWith4HourInterval(epoch2));

        // Test hour 2 (02:xx:xx) should round to 00:00:00
        long epoch3 = 1609466400L; // 2021-01-01 02:00:00 UTC
        System.out.println("Input: " + epoch3 + " -> Output: " + solution.convertEpochToDateKeyWith4HourInterval(epoch3));

        // Test hour 3 (03:xx:xx) should round to 00:00:00
        long epoch4 = 1609470000L; // 2021-01-01 03:59:59 UTC (approximately)
        System.out.println("Input: " + epoch4 + " -> Output: " + solution.convertEpochToDateKeyWith4HourInterval(epoch4));

        // Test hour 4 (04:xx:xx) should round to 04:00:00
        long epoch5 = 1609473600L; // 2021-01-01 04:00:00 UTC
        System.out.println("Input: " + epoch5 + " -> Output: " + solution.convertEpochToDateKeyWith4HourInterval(epoch5));

        // Test hour 5 (05:xx:xx) should round to 04:00:00
        long epoch6 = 1609477200L; // 2021-01-01 05:00:00 UTC
        System.out.println("Input: " + epoch6 + " -> Output: " + solution.convertEpochToDateKeyWith4HourInterval(epoch6));

        // Test hour 8 (08:xx:xx) should round to 08:00:00
        long epoch7 = 1609488000L; // 2021-01-01 08:00:00 UTC
        System.out.println("Input: " + epoch7 + " -> Output: " + solution.convertEpochToDateKeyWith4HourInterval(epoch7));

        // Test hour 12 (12:xx:xx) should round to 12:00:00
        long epoch8 = 1609502400L; // 2021-01-01 12:00:00 UTC
        System.out.println("Input: " + epoch8 + " -> Output: " + solution.convertEpochToDateKeyWith4HourInterval(epoch8));

        // Test hour 16 (16:xx:xx) should round to 16:00:00
        long epoch9 = 1609516800L; // 2021-01-01 16:00:00 UTC
        System.out.println("Input: " + epoch9 + " -> Output: " + solution.convertEpochToDateKeyWith4HourInterval(epoch9));

        // Test hour 20 (20:xx:xx) should round to 20:00:00
        long epoch10 = 1609531200L; // 2021-01-01 20:00:00 UTC
        System.out.println("Input: " + epoch10 + " -> Output: " + solution.convertEpochToDateKeyWith4HourInterval(epoch10));

        // Test hour 23 (23:xx:xx) should round to 20:00:00
        long epoch11 = 1609545600L; // 2021-01-01 23:59:59 UTC (approximately)
        System.out.println("Input: " + epoch11 + " -> Output: " + solution.convertEpochToDateKeyWith4HourInterval(epoch11));

        // Test with a different date to ensure format is consistent
        long epoch12 = 1640995200L; // 2022-01-01 00:00:00 UTC
        System.out.println("Input: " + epoch12 + " -> Output: " + solution.convertEpochToDateKeyWith4HourInterval(epoch12));

        // Test around month boundary
        long epoch13 = 1609459199L; // 2020-12-31 23:59:59 UTC
        System.out.println("Input: " + epoch13 + " -> Output: " + solution.convertEpochToDateKeyWith4HourInterval(epoch13));
    }
}