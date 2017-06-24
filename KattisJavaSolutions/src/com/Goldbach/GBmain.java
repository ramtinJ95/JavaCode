package com.Goldbach;

/**
 * Created by Ramtin on 2017-06-24.
 */
public class GBmain {

    public static void main(String[] args){
       /* Goldbach gb = new Goldbach();
        gb.readIn();
        gb.solve(gb.input);

        */
        Goldbach gb = new Goldbach();
        Goldbach2 gb2 = new Goldbach2();
        gb.readIn();

        for(int i = 0; i < gb.input.size(); i++){
            gb2.Goldbach(gb.input.get(i));
        }



    }
}
