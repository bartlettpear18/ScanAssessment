package com.company;
/**
 * Created by Joel.Bartlett18 on 6/29/2017.
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
//
//import static com.company.Encoder.readXMLToBarcodeList;
//import static com.company.Encoder.writeBarcodeListToXML;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
//
//        //Application.launch(Display.class,args);
//        ArrayList<Barcode> barcodes = new ArrayList<Barcode>();
//        Barcode b1 = new Barcode("1234", "bology");
//        Barcode b2 = new Barcode("5678", "sym");
//        barcodes.add(b1);
//        barcodes.add(b2);
//
//        Encoder array = new Encoder();
//        array.setBarcodeArrayList(barcodes);
//        System.out.println(array.toString());
//
//        writeBarcodeListToXML(array);
//        //readXMLToBarcodeList();

        Server server = new Server();
        server.start();
    }
}


/*

Server server = new Server()
server.execute() ?

Analyze analyze = Analyze.getInstance();
analyze.updateScanners();

launch args

----model run tests ---
Scanner currentScanner = new Scanner(model, decoder board, LANGUAGE);

--> tell driver and app to go

app singleton will relay data received to agent singleton/agent driver socket

--> wait for data

currentScanner.setAverageExecutionTime();
currentScanner.setPercentError();
currentScanner.addSym(sym); ///for all failed strings received

analyze.addScanner(currentScanner);
analyze.analyze(); --> set public static observablelist or something to display data

--> show table view

*/
