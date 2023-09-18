package com.testdevlab.utilities;


public class BrowserUtils {
    //Here I will put all the methods tht I am going to use
    //I will not use .sleep() method.
    /**
     * This method will accept int (in seconds) and execute Thread.sleep()
     * for given duration
     *
     * @param second
     */
    public static void sleep(int second) {
        second *= 1000;
        try {
            Thread.sleep(second);
        } catch (InterruptedException e) {

        }
    }


}
