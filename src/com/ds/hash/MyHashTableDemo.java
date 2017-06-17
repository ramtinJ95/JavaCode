package com.ds.hash;

/**
 * Created by Ramtin on 2017-06-18.
 */
public class MyHashTableDemo {
    public static void main(String[] args){

        MyHashTable<String> ht = new MyHashTable<>(10);
        ht.put(11, "ramos");
        ht.put(11, "Ramtino");
        ht.put(11, "Han solo");
        ht.put(13, "ben solo");
        ht.put(11, "tina");
        System.out.println(ht.get(11));
    }
}
