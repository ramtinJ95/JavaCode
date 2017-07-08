package com.BalancedDiet;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * Created by Ramtin on 2017-06-24.
 */
public class BalancedDiet {

    public Hashtable<Integer, ArrayList<Integer>> input = new Hashtable<>();
    public String data = " ";
    public ArrayList<Integer> keys = new ArrayList<>();


    public BalancedDiet(){

    }
    public void readIn(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> cans = new ArrayList<>();
        data = sc.nextLine();
        String[] tokens = data.split(" ");
        for(int i = 1; i < tokens.length; i++){
            cans.add(Integer.parseInt(tokens[i]));
        }
        keys.add(Integer.parseInt(tokens[0]));
        input.put(Integer.parseInt(tokens[0]), cans);
    }

    public void solve(Hashtable<Integer, ArrayList<Integer>> input ){
        int answer;
        int sum;
        


    }

}
