package com.SimpleAddition;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by Ramtin on 2017-06-25.
 */
public class SAmain {

    public static void main (String[] args) throws java.lang.Exception
    {
        Scanner sc=new Scanner(System.in);
        String s;
        s=sc.next();
        BigInteger a=new BigInteger(s);
        s=sc.next();
        BigInteger b=new BigInteger(s);
        b=b.add(a);
        System.out.println(b.toString());
    }
}
