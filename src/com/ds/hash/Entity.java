package com.ds.hash;

/**
 * Created by Ramtin on 2017-06-17.
 */
public class Entity {

    int key;
    Object value;
    Entity next;

    public Entity(int key, Object value){
        this.key = key;
        this.value = value;
        next = null;
    }
    public Entity(){
        next = null;
    }

    public int getKey(){
        return key;
    }

    public Object getValue(){
        return value;
    }
}
