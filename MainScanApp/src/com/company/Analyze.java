package com.company;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Joel.Bartlett18 on 6/29/2017.
 */
public class Analyze {

    private ArrayList<Scanner> scanners;
    private ArrayList<Scanner> temp = new ArrayList<Scanner>();
    private String location;  //////////////////////////////////////////////MAKE THIS INTO A CONSTANT AND IND DIRECTORY (LINE 77 check out tmp)

    private static Analyze ourInstance = new Analyze();

    public static Analyze getInstance() {
        return ourInstance;
    }

    private Analyze() {
        scanners = new ArrayList<Scanner>();
    }

    /**
     * add a scanner to the Scanners ArrayList
     * @param scanner
     */
    public void addScanner(Scanner scanner) {
        scanners.add(scanner);
    }

    /**
     * remove a scanner from the Scanners ArrayList
     * @param scanner
     */
    public void removeScanner(Scanner scanner) {
        scanners.remove(scanner);
    }

    /**
     * checks to see if the parameter scanner is in the scanners ArrayList
     * @param tempScan
     * @return
     */
    private boolean isInScanners(Scanner tempScan) {
        boolean scannerContained = false;
        if(scanners.size()>0) {
            for (Scanner scan : scanners) {
                if (tempScan.equals(scan)) {
                    scannerContained = true;
                }
            }
        }
        return scannerContained;
    }

    /**
     * Adds all non-reocurring Scanners from serialized file to Scanner ArrayList
     */
    private void addScanners() {
        for (Scanner scan : temp) {
            if(!isInScanners(scan)) {
                addScanner(scan);
            }
        }
    }

    /**
     * Deserializes file to objects and adds them to a temporary array list
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readData() throws IOException, ClassNotFoundException {

        //Serialize setup
        FileInputStream file = new FileInputStream("data.tmp");
        ObjectInputStream in = new ObjectInputStream(file);

        //Temporary ArrayList for scanner objects from file
        if (file.available() != 0) {
            while(in!=null && in.available()!=0) {
                scanners.add((Scanner) in.readObject());
            }
        }
    }

    //Call on start
    /**
     * Single method to update Scanners array list with previous data
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void updateScanners() throws IOException, ClassNotFoundException {
        readData();
        addScanners();
        temp.clear();
        System.out.println("Scanners is updated with previous data");
    }

    //Call on exit
    /**
     * Save all current scanners ArrayList data to .tmp file
     * @throws IOException
     */
    private void saveData() throws IOException {
        FileOutputStream fileOut = new FileOutputStream(location);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        for (Scanner scan : scanners) {
            out.writeObject(scan);
        }
        out.close();
        fileOut.close();
        System.out.println("Serialized Scanner data at " + location);
    }




    //Data Analysis




}
