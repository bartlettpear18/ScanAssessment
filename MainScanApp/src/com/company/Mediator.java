package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Joel.Bartlett18 on 7/24/2017.
 */
public class Mediator {

    private boolean isReady = false;
    private boolean isDone = false;
    private ArrayList<String> decodes = new ArrayList<>();
    private ArrayList<String> codes = new ArrayList<>();

    private int numTests = 10;
    private double avgExecutionTime = 0;

    public static String ready = new String("Ready");


    public void changeIsReady() { isReady = !isReady;}
    public void changeIsDone() { isDone = !isDone; }
    public void addDecodes(String input) { decodes.add(input); }
    public void addCodes(String input) { codes.add(input); }
    public void setAvgExecutionTime(double time) { avgExecutionTime = time; }

    public boolean getIsReady() { return isReady; }
    public boolean getIsDone() { return isDone; }
    public ArrayList<String> getDecodes() { return decodes; }
    public ArrayList<String> getCodes() { return codes; }
    public int getTests() { return numTests; }
    public double getAvgExecutionTime() { return avgExecutionTime; }

    public double compare() {
        int count = 0;
        int length = codes.size();

        for (int i = 0; i < decodes.size(); i++) {
            String tmpDecode = decodes.get(i);
            String tmpCode = codes.get(i);
            System.out.println("Decode: " + tmpDecode + " Code: " + tmpCode);

            if(tmpCode.equals(tmpDecode)) {
                System.out.println("Compare true");
                count++;
            }
        }
        System.out.println("Count is: " +  count + " Out of: " + length);
        double percent = (double) count / (double) length;
        System.out.println(percent);
        percent = percent * 100;
        return percent;
    }

}
