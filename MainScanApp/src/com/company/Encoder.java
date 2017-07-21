package com.company;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class Encoder {

    public static final String xmlName = "data.xml";

    public Encoder(){}

    public static void writeBarcodeListToXML(Encoder bar) throws FileNotFoundException {
        XMLEncoder encoder = null;
        encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(xmlName)));
        encoder.writeObject(bar);
        encoder.close();
    }

    public static void readXMLToBarcodeList() throws FileNotFoundException {
        XMLDecoder decoder = null;
        decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(xmlName)));
        Encoder barcodes = (Encoder) decoder.readObject();
        System.out.println(barcodes.toString());
    }

}
