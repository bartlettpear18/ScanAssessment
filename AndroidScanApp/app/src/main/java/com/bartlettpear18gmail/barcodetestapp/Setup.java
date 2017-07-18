//package com.bartlettpear18gmail.barcodetestapp;
//
//
//import java.util.ArrayList;
//import java.util.Random;
//
///**
// * Created by Joel.Bartlett18 on 6/30/2017.
// */
//public class Setup {
//
//    private static Setup ourInstance = new Setup();
//
//    public static Setup getInstance() {
//        return ourInstance;
//    }
//
//
//    private ArrayList<AndroidBarcode> barcodes = new ArrayList<AndroidBarcode>();
//
//    private int length = 20;
//
//    private final AndroidBarcode upcA = new AndroidBarcode("012345678905", "UPC-A", R.drawable.upca);
//    private final AndroidBarcode upcE = new AndroidBarcode("01236432", "UPC-E", R.drawable.upce);
//    private final AndroidBarcode upcE1 = new AndroidBarcode("12345670", "UPC-E1", R.drawable.upce1);
//    private final AndroidBarcode ean8 = new AndroidBarcode("40153476", "EAN-8", R.drawable.ean8);
//    private final AndroidBarcode ean13 = new AndroidBarcode("4498765301535", "EAN-13", R.drawable.ean13);
//    private final AndroidBarcode bkEAN = new AndroidBarcode("9780911261097", "Bookland EAN", R.drawable.book);
//    private final AndroidBarcode c128 = new AndroidBarcode("CODE128", "Code 128", R.drawable.code128);
//    private final AndroidBarcode ean128 = new AndroidBarcode("CODEAFNC1", "EAN-128", R.drawable.ean128);
//    private final AndroidBarcode c39 = new AndroidBarcode("CODE", "Code 39", R.drawable.code39);
//    private final AndroidBarcode triC39 = new AndroidBarcode("$ABD234$", "Trioptic Code 39", R.drawable.tri);
//    private final AndroidBarcode c93 = new AndroidBarcode("54321", "Code 93", R.drawable.code93);
//    private final AndroidBarcode inter = new AndroidBarcode("01234567890128", "Interleaved 2 of 5", R.drawable.interleaved);
//    private final AndroidBarcode discrete = new AndroidBarcode("123440153471", "Discrete 2 of 5", R.drawable.discrete);
//    private final AndroidBarcode codabar = new AndroidBarcode("A$12345B", "Codabar", R.drawable.codebar);
//    private final AndroidBarcode msi = new AndroidBarcode("2020597", "MSI Plessy", R.drawable.msi);
//    private final AndroidBarcode rss = new AndroidBarcode("20012345678909", "RSS14", R.drawable.rss);
//    private final AndroidBarcode rssLim = new AndroidBarcode("15012345678907", "RSS LIM", R.drawable.rsslim);
//    private final AndroidBarcode qr = new AndroidBarcode("replace", "QR Code", R.drawable.qr);
//    private final AndroidBarcode pdf = new AndroidBarcode("replace", "PDF 417", R.drawable.pdf);
//    private final AndroidBarcode dataMatrix = new AndroidBarcode("replace", "Data Matrix", R.drawable.datamatrix);
//
//    //not doing post nets and rss exp
//
//
//    //File stuff here
//
//    public Setup() {
//        //Make objects for every barcode
//        barcodes.add(upcA);
//        barcodes.add(upcE);
//        barcodes.add(upcE1);
//        barcodes.add(ean8);
//        barcodes.add(ean13);
//        barcodes.add(bkEAN);
//        barcodes.add(c128);
//        barcodes.add(ean128);
//        barcodes.add(c39);
//        barcodes.add(triC39);
//        barcodes.add(c93);
//        barcodes.add(inter);
//        barcodes.add(discrete);
//        barcodes.add(codabar);
//        barcodes.add(msi);
//        barcodes.add(rss);
//        barcodes.add(rssLim);
//        barcodes.add(qr);
//        barcodes.add(pdf);
//        barcodes.add(dataMatrix);
//    }
//
//    public static int randomInt(int length) {
//        Random rn = new Random();
//        int n = length + 1;
//        int randomNum = rn.nextInt() % n;
//        return randomNum;
//    }
//
//    public ArrayList<AndroidBarcode> randomize() {
//        ArrayList<AndroidBarcode> temp = new ArrayList<AndroidBarcode>();
//        ArrayList<AndroidBarcode> runArray = new ArrayList<AndroidBarcode>();
//        for(AndroidBarcode obj : barcodes) {
//            temp.add(obj);
//        }
//        /*
//        int length = temp.size()-1;
//        for(int i = 0; i < length; i++) {
//            int j = randomInt(length-i);
//            runArray.add(temp.get(j));
//            temp.remove(j);
//        }
//        */
//
//        return temp;
//    }
//}
