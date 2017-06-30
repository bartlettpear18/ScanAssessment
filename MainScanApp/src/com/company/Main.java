package com.company;
/**
 * Created by Joel.Bartlett18 on 6/29/2017.
 */

import javafx.application.Application;

public class Main {

    public static void main(String[] args) {
        Application.launch(Display.class,args);
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
