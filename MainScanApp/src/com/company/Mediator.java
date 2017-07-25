package com.company;

/**
 * Created by Joel.Bartlett18 on 7/24/2017.
 */
public class Mediator {

    private boolean isReady = false;
    private boolean isScanComplete = false;
    private boolean results = false;


    public static String ready = new String("Ready");


    public void changeIsReady() { isReady = !isReady;}
    public void changeIsScanComplete() { isScanComplete = !isScanComplete; }
    public void changeResults() { results = !results; }


    public boolean getIsReady() { return isReady; }
    public boolean getIsScanComplete() { return isScanComplete; }
    public boolean getDone() { return results; }

}
