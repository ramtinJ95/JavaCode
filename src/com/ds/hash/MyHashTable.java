package com.ds.hash;

/**
 * Created by Ramtin on 2017-06-17.
 */
public class MyHashTable<T> {

    Entity[] arrayHash;
    int size;

    public MyHashTable(int size){
        this.size = size;
        arrayHash = new Entity[size];
        for(int i = 0; i<size; i++){
            arrayHash[i] = new Entity();
        }

    }
    // random hash function, this hash sorting requirement can be modifed as one sees fit for a given problem
    public int getHash(int key){
        return key % size;
    }

    public void put(int key, Object value){
        int hashIndex = getHash(key);
        Entity arrayValue = arrayHash[hashIndex];
        Entity newItem = new Entity(key, value);
        newItem.next = arrayValue.next;
        arrayValue.next = newItem;

    }

    public T get(int key){
        T value = null;
        int hashIndex = getHash(key);
        Entity arrayValue = arrayHash[hashIndex];
        while(arrayValue != null){
            if(arrayValue.getKey() == key){
                Object[] values;
                value = (T) arrayValue.getValue();
                arrayValue = arrayValue.next;
                continue;
            }
            arrayValue = arrayValue.next;
        }

        return value;
    }

}
