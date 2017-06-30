package com.company;

/**
 * Created by Joel.Bartlett18 on 6/29/2017.
 * Same Class will be used in App
 */

public class TestBarcode {

    private String decodedData;
    private String symbology;

    private TestBarcode(String decodedData, String symbology) {
        this.decodedData = decodedData;
        this.symbology = symbology;
    }

    //Accessor and Mutator methods
    public String getDecodedData() {
        return decodedData;
    }

    public void setDecodedData(String decodedData) {
        this.decodedData = decodedData;
    }

    public String getSymbology() {
        return symbology;
    }

    public void setSymbology(String symbology) {
        this.symbology = symbology;
    }

}