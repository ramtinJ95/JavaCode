package com.Recount;

/**
 * Created by Ramtin on 2017-06-23.
 */
public class Rmain {

    public static void main(String[] args){
        Recount rc = new Recount();
        rc.readIn();
        rc.countVotes(rc.list);
        rc.getWinner(rc.votes);
    }
}
