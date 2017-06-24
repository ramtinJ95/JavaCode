package com.Goldbach;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Ramtin on 2017-06-24.
 *
 * Absolute brute force, gives correct answer but terribly slow needs optimizing,
 */
public class Goldbach {

    public int representation = 0;
    public ArrayList<Integer> input = new ArrayList<>();



    public Goldbach(){

    }

    public void readIn(){
        Scanner sc = new Scanner(System.in);
        int numbLines = sc.nextInt();
        for(int i = 0; i < numbLines; i++){
            input.add(sc.nextInt());
        }
        sc.close();
    }

    public void solve(ArrayList<Integer> input){
        boolean random = false;

        for(int i = 0; i < input.size(); i++){
            ArrayList<String> representations = new ArrayList<>();
            ArrayList<Integer> primes = new ArrayList<>();
            int temp = input.get(i);
            findprimes(temp, primes);
            for(int j = 0; j < primes.size(); j++) {
                for (int k = 0; k < primes.size(); k++) {
                    int a = primes.get(j);
                    int b = primes.get(k);
                    if(a + b == temp && a == b){
                        random = true;
                        representation++;
                        if(representations.contains(a+"+" + b) || representations.contains(b+ "+"+a)){
                            continue;
                        }
                        else{
                            representations.add(a+ "+"+b);
                        }
                    }
                    else if(a+ b == temp && !(a==b)){
                        representation++;
                        if(representations.contains(a+"+" + b) || representations.contains(b+ "+"+a)){
                            continue;
                        }
                        else{
                            representations.add(a + "+" + b);
                        }
                    }
                }
            }
            if(random){
                representation = (representation /2) +1;
            }
            else{
                representation = representation/2;
            }
            printAnswer(i, representations);
            representation = 0;
            random = false;
        }
    }

    private void printAnswer(int index, ArrayList<String> representations) {
        System.out.println(input.get(index)+ " has " + representation +" representation(s)");
        for(int i = 0; i < representations.size(); i++){
            System.out.println(representations.get(i));
        }
        System.out.println();

    }

    private void findprimes(int temp, ArrayList<Integer> primes) {
        if(temp == 4){
            primes.add(0,2);
            return;
        }
        for(int i = 2; i < temp; i++){
            if(i == 2){
                primes.add(0,2);
                continue;
            }
            if(isPrime(i)){
                primes.add(i);
            }
        }
    }

    public boolean isPrime(int n){
        if(n % 2 == 0){
            return false;
        }
        else if(n == 2){
            return true;
        }
        else
        {
            for(int i = 3; i*i <= n; i += 2){
                if(n % i == 0){
                    return false;
                }
            }
        }
        return true;

    }

}
