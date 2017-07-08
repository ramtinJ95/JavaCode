package com.iHateNumberNine;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by Ramtin on 2017-06-25.
 */
public class IHNNmain {

    static String solve(BigInteger b)
    {
        b =b.subtract(BigInteger.valueOf(1));
        BigInteger tres=(BigInteger.valueOf(8).multiply(BigInteger.valueOf(9).modPow(b, BigInteger.valueOf(1000000007)))).mod(BigInteger.valueOf(1000000007));
        return tres.toString();
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int tc=sc.nextInt();
        String temp;
        for(int i=0;i<tc;i++)
        {
            temp=sc.next();
            System.out.println(solve(new BigInteger(temp)));
        }
    }
}
