package com.SavingDaylight;


import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Ramtin on 2017-08-26.
 */
public class MainSDL {
    public static void main(String[] args){
        new MainSDL();
    }

    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<String> result = new ArrayList<>();

   public MainSDL() {
        readInDates();
        scanDates();
        printResult();
    }

    private void readInDates() {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            list.add(scan.nextLine());
        }
        scan.close();
    }

    private void scanDates() {
        for (String string : list) {
            String[] stringSplit = string.split(" ");
            int[] sunR = checkSunRise(stringSplit[3]);
            int[] sunS = checkSunSet(stringSplit[4]);
            int[] diff = calculateDiffrence(sunR, sunS);
            fixResultPrint(string, diff);
        }
    }

    private void fixResultPrint(String date, int[] diff) {
        String[] dateSplit = date.split(" ");

        result.add(dateSplit[0] + " " + dateSplit[1] + " " + dateSplit[2] + " "
                + diff[0] + " hours " + diff[1] + " minutes");

    }

    private int[] calculateDiffrence(int[] sunR, int[] sunS) {
        sunS[1] -= sunR[1];
        sunS[0] -= sunR[0];
        if (sunS[1] < 0) {
            sunS[1] += 60;
            sunS[0] -= 1;
        }
        if (sunS[0] < 0) {
            sunS[1] += 24;
        }
        return sunS;
    }

    private int[] checkSunRise(String sunriseString) {
        String[] date = sunriseString.split(":");
        int[] time = new int[2];
        time[0] = Integer.parseInt(date[0]);
        time[1] = Integer.parseInt(date[1]);
        return time;
    }

    private int[] checkSunSet(String sunrisetString) {
        String[] date = sunrisetString.split(":");
        int[] time = new int[2];
        time[0] = Integer.parseInt(date[0]);
        time[1] = Integer.parseInt(date[1]);
        return time;
    }

    private void printResult() {
        for (String string : result) {
            System.out.println(string);
        }
    }


}
