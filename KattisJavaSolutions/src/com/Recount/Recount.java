package com.Recount;

import java.util.*;

/**
 * Created by Ramtin on 2017-06-23.
 */
public class Recount{
    public  ArrayList<String> list = new ArrayList<>();
    public HashMap<String, Integer> votes = new HashMap<>();


    public Recount(){
    }

    public void readIn(){
        Scanner sc = new Scanner(System.in);
        String temp;
        while(sc.hasNextLine()){
            temp = sc.nextLine();
            if(temp.equals("***")){
                break;
            }
            list.add(temp);
        }
    }

    public void countVotes(ArrayList<String> list){
        for(int i = 0; i < list.size(); i++){
            if(votes.get(list.get(i)) != null){
                int temp = votes.get(list.get(i)) + 1;
                votes.put(list.get(i), temp);
            }
            else{
                votes.put(list.get(i), 0);
            }
        }
    }

    public void getWinner(HashMap<String, Integer> votes){
        int maxValueInMap = Collections.max(votes.values());
        ArrayList<String> temp = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : votes.entrySet()){
            if(entry.getValue() == maxValueInMap){
                temp.add(entry.getKey());
            }
        }
        if(temp.size() > 1){
            System.out.println("Runoff!");
        }
        else{
            System.out.println(temp.get(0));
        }
    }


}
