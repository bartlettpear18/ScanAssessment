package com.company;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Joel.Bartlett18 on 6/29/2017.
 * Same class will be used in Driver
 */
public class Scanner implements Serializable{

    private String model;
    private String decoder = "none";
    private String driverLanguage;
    private double averageExecutionTime;
    private double averagePercentError;
    private ArrayList<String> failedSymbologies = new ArrayList<String>();

    public void Scanner(String model, String decoder, String language) {
        this.model = model;
        this.decoder = decoder;
        this.driverLanguage = language;
    }

    //Getter Methods
    public String getModel() {
        return model;
    }

    public String getDecoder() {
        return decoder;
    }

    public String getDriverLanguage() {
        return driverLanguage;
    }

    public double getAverageExecutionTime() {
        return averageExecutionTime;
    }

    public double getAveragePercentError() {
        return averagePercentError;
    }

    //Setter Methods
    public void setModel(String model) {
        this.model = model;
    }

    public void setDecoder(String decoder) {
        this.decoder = decoder;
    }

    public void setDriverLanguage(String driverLanguage) {
        this.driverLanguage = driverLanguage;
    }

    public void setAverageExecutionTime(double averageExecutionTime) {
        this.averageExecutionTime = averageExecutionTime;
    }

    public void setAveragePercentError(double averagePercentError) {
        this.averagePercentError = averagePercentError;
    }

    /**
     * toString for failed symbologies array list
     * @return
     */
    public String symString() {
        String failedSym = "";
        for (String sym : failedSymbologies) {
            failedSym += sym + "\n";
        }
        return failedSym;
    }

    /**
     * Appends new failed symbology to array list
     * @param sym
     */
    public void addSym(String sym) {
        failedSymbologies.add(sym);
    }

    public boolean equals (Scanner otherScanner) {
        if (model.equals(otherScanner.getModel())) {
            return true;
        } else {
            return false;
        }
    }

}
