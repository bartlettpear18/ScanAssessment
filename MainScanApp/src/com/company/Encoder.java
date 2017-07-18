//package com.company;
//
//import com.company.scan.Barcode;
//
//import java.beans.XMLDecoder;
//import java.beans.XMLEncoder;
//import java.io.*;
//import java.util.ArrayList;
//
///**
// * Created by Joel.Bartlett18 on 7/13/2017.
// */
//public class Encoder {
//
//    private ArrayList<Barcode> barcodeArrayList = new ArrayList<Barcode>();
//    public static final String xmlName = "barcodeList.xml";
//
//    public Encoder(){}
//
//    public ArrayList<Barcode> getBarcodeArrayList() {
//        return barcodeArrayList;
//    }
//
//    public void setBarcodeArrayList (ArrayList<Barcode> barcodes) {
//        barcodeArrayList = barcodes;
//    }//
//    public String toString() {
//        String barcodes = "";
//        for (Barcode barcode : getBarcodeArrayList()) {
//            barcodes += barcode.getSymbology() + "\n";
//        }
//
//        return barcodes;
//    }
//
//
//    public static void writeBarcodeListToXML(Encoder bar) throws FileNotFoundException {
//        XMLEncoder encoder = null;
//        encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(xmlName)));
//        encoder.writeObject(bar);
//        encoder.close();
//    }
//
//    public static void readXMLToBarcodeList() throws FileNotFoundException {
//        XMLDecoder decoder = null;
//        decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(xmlName)));
//        Encoder barcodes = (Encoder) decoder.readObject();
//        System.out.println(barcodes.toString());
//    }
//
//}
