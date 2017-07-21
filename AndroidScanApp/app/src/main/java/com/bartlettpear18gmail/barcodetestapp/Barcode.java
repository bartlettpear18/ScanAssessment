package com.bartlettpear18gmail.barcodetestapp;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;

import java.io.Serializable;

public class Barcode implements Serializable {

    private String decodedData;
    private String symbology;
    private Bitmap imageBit;

    public Barcode() {}

    public String getDecodedData() {
        return this.decodedData;
    }
    public String getSymbology() {
        return this.symbology;
    }
    public Bitmap getImageBit() { return this.imageBit; }

    public void setDecodedData(String decodedData) {
        this.decodedData = decodedData;
    }
    public void setSymbology(String symbology) {
        this.symbology = symbology;
    }
    public void setImageBit (Bitmap bitmap) { this.imageBit = bitmap; }

    public Barcode(String decodedData, String symbology, Bitmap imageBit) {
        this.decodedData = decodedData;
        this.symbology = symbology;
        this.imageBit = imageBit;
    }
}
