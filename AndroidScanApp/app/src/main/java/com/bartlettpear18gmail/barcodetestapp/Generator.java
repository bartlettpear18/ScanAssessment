package com.bartlettpear18gmail.barcodetestapp;

import android.graphics.Bitmap;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;

/**
 * Created by Joel.Bartlett18 on 7/18/2017.
 */
public class Generator {

    private ArrayList<BarcodeFormat> linear = new ArrayList<BarcodeFormat>();
    private ArrayList<BarcodeFormat> twoDimensional = new ArrayList<BarcodeFormat>();

    private ArrayList<String> formats = new ArrayList<String>(numTests);

    private ArrayList<Bitmap> oneDMaps = new ArrayList<Bitmap>();
    private ArrayList<Bitmap> twoDMaps = new ArrayList<Bitmap>();
    private ArrayList<Bitmap> mix = new ArrayList<Bitmap>();


    public static final int numTests = 10;
    private int decodeData;
    private String tag = "Debug";


    public Generator() throws WriterException {
        generateCode();
        instantiate1D();
        instantiate2D();
    }

    //Accessor
    public String get1DFormat(int loc) {
        String format = linear.get(loc).toString();
        return format;
    }
    public String get2DFormat(int loc) {
        String format = twoDimensional.get(loc).toString();
        return format;
    }

    public ArrayList<Bitmap> getOneDMaps() {
        return oneDMaps;
    }
    public ArrayList<Bitmap> getTwoDMaps() {
        return twoDMaps;
    }
    public ArrayList<Bitmap> getMix() {
        return mix;
    }

    public ArrayList<String> getFormats() { return formats; }

    public int getDecodeData() { return decodeData; }

    //Setup
    public void instantiate1D() throws WriterException {
//        linear.add(BarcodeFormat.CODE_39);
        linear.add(BarcodeFormat.CODABAR);
//        linear.add(BarcodeFormat.CODE_128);
    }

    public void instantiate2D() throws WriterException {
        twoDimensional.add(BarcodeFormat.QR_CODE);
        twoDimensional.add(BarcodeFormat.DATA_MATRIX);
        twoDimensional.add(BarcodeFormat.PDF_417);
    }

    /**
     * All the barcodes displayed at the test will have the same randomly generated code
     * @return
     */
    private void generateCode() {
        int start = 100000;
        int end = 1000000;
        int code = Math.abs((int) (Math.random()*(start-end)+start));
        decodeData = code;
        Log.d(tag, "Generated code: " + decodeData);
    }

    /**
     * To randomize the order of Barcodes displayed
     * @param range
     * @return
     */
    private int randomLoc(int range) {
        int rand = (int) (Math.random()*range);
        return rand;
    }

    public Bitmap makeBitmap(int data, BarcodeFormat format) throws WriterException {
        String code = data + "";
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        BitMatrix bitMatrix = multiFormatWriter.encode(code, format, 200, 200);
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
        return bitmap;
    }

    public void make1D() throws WriterException {

        ArrayList<Bitmap> barcodes = new ArrayList<Bitmap>(numTests);
        int range = linear.size();
        for(int i = 0; i < numTests-1; i++) {
            int loc = randomLoc(range);
            Bitmap map = makeBitmap(decodeData, linear.get(loc));
            barcodes.add(map);
            formats.add(get1DFormat(loc));
            Log.d(tag, "Map created for: " + formats.get(i));
        }
        oneDMaps = barcodes;
    }

    public void make2D() throws WriterException {

        ArrayList<Bitmap> barcodes = new ArrayList<Bitmap>(numTests);
        int range = twoDimensional.size();

        for(int i = 0; i < numTests-1; i++) {
            int loc = randomLoc(range);
            Bitmap map = makeBitmap(decodeData, twoDimensional.get(loc));
            barcodes.add(map);
            formats.add(get2DFormat(loc));
            Log.d(tag, "Map created for: " + formats.get(i));
        }
        twoDMaps = barcodes;
    }

    public void makeMix() throws WriterException {

        ArrayList<Bitmap> barcodes = new ArrayList<Bitmap>(numTests);
        int length1 = linear.size();
        int length2 = twoDimensional.size();

        for (int i =0 ; i < numTests-1; i++) {
            if(i%2==1) {
                int loc = randomLoc(length1);
                Bitmap map = makeBitmap(decodeData, linear.get(loc));
                barcodes.add(map);
                formats.add(get1DFormat(loc));
                Log.d(tag, "Map created for: " + formats.get(i));
            } else {
                int loc = randomLoc(length2);
                Bitmap map = makeBitmap(decodeData, twoDimensional.get(loc));
                barcodes.add(map);
                formats.add(get2DFormat(loc));
                Log.d(tag, "Map created for: " + formats.get(i));
            }
        }
        mix = barcodes;
    }
}