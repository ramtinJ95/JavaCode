package com.BalancedDiet;

import java.util.*;

/**
 * Created by Ramtin on 2017-06-24.
 */
public class BalancedDiet {
    static int[][] K;

    public static int calculate(int[] nums , int n , int W) {

        for (int i = 0; i < K.length; i++)
            for (int j = 0; j < K[0].length; j++)
            {
                if (i == 0 || j == 0)
                    K[i][j] = 0;

                else if (nums[i - 1] > j)
                    K[i][j] = K[i - 1][j];

                else
                    K[i][j] = Math.max(K[i - 1][j] , nums[i - 1] + K[i - 1][j - nums[i - 1]]);
            }

        return K[n][W];
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (true)
        {
            int n = scan.nextInt();

            if (n == 0)
                break;

            int[] cals = new int[n];
            int sum = 0;

            for (int i = 0; i < cals.length; i++)
            {
                cals[i] = scan.nextInt();
                sum += cals[i];
            }

            K = new int[n + 1][sum / 2 + 1];

            int small = calculate(cals , n , sum / 2);
            int big = sum - small;

            System.out.println(big + " " + small);
        }

        scan.close();
    }
}
