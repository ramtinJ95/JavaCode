package com.BalancedDiet;

/**
 * Created by Ramtin on 2017-06-24.
 */
public class BDmain {
    public static void main(String[] args){
        BalancedDiet bd = new BalancedDiet();
       while(!bd.data.equals("0")) {
           bd.readIn();
       }
        System.out.println(bd.input.toString());
        System.out.println(bd.input.values());
        System.out.println(bd.input.get(1));
        System.out.println(bd.input.keySet().size());

    }
}
