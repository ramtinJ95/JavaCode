package com.GuessTheNumber;

import java.util.Scanner;

/**
 * Created by Ramtin on 2017-06-24.
 */
public class GuessTheNumber {

    public String data = "";

    private int max = 1001;
    private int min = 1;
    private int guess = 500;

    public GuessTheNumber(){

    }
    public void readIn(){
        Scanner sc = new Scanner(System.in);
        data = sc.nextLine();
        if(data.equals("lower")){
            nextGuess(1);
            generateGuess();
        }
        else if(data.equals("higher")){
            nextGuess(2);
            generateGuess();
        }

    }

    private void nextGuess(int controller) {
        if(controller == 1){
            max = guess;
        }
        else if(controller == 2){
            min = guess;
        }
    }
    private void generateGuess(){
        guess = (max + min ) / 2;
        System.out.println(guess);
    }
}
