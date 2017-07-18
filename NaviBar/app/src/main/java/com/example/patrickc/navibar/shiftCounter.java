package com.example.patrickc.navibar;

/**
 * Created by Windows 10 on 18/07/2017.
 */

class shiftCounter {
    private static final shiftCounter ourInstance = new shiftCounter();

    static shiftCounter getInstance() {
        return ourInstance;
    }
    int shiftNumber =0;
    private shiftCounter() {

    }

    protected void increaseShift(){
        shiftNumber++;
    }

    protected void resetShift(){
        shiftNumber = 0;
    }

    protected int getShiftNumber(){
        return shiftNumber;
    }
}
