package com.GuessTheNumber;

/**
 * Created by Ramtin on 2017-06-24.
 */
public class GTNmain {
    public static void main(String[] args){
        GuessTheNumber gtn = new GuessTheNumber();
        System.out.println(500);
        while(!gtn.data.equals("correct")){
            gtn.readIn();
        }
    }
}
