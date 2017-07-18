package com.example.patrickc.navibar;

/**
 * Created by Windows 10 on 18/07/2017.
 */

public class Counter {
    int count =0;


    public Counter(){

    }

    protected void setCount(){
        count++;
    }

    protected void resetCount(){
        count = 0;
    }


    protected int getCount(){
        return count;
    }

    protected void removeCount(){
        count--;
    }

}
