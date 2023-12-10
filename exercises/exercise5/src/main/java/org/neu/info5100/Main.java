/*
INFO5100 EXERCISE5
DATE: Dec/09/2023
STUDENT FULL NAME: Xinzhe Yuan
NUID: 002641096
 */
package org.neu.info5100;

public class Main {
    public static void main(String[] args) {
        //Singleton Pattern
        Router router = Router.getInstance();

        //Singleton Pattern & State Pattern
        router.powerOn();

        //Observer Pattern
        TerminalDevice laptop = new TerminalDevice("Laptop");
        TerminalDevice desktop = new TerminalDevice("Desktop");
        TerminalDevice playStation = new TerminalDevice("PlayStation");

        //Singleton Pattern & Observer Pattern
        router.addObserver(laptop);
        router.addObserver(desktop);
        router.addObserver(playStation);

        //State Pattern & Singleton Pattern
        router.powerOn();

        //Singleton Pattern & Observer Pattern
        router.removeObserver(laptop);

        //Singleton Pattern
        router.connectInternet();

        //State Pattern & Singleton Pattern
        router.powerOff();
    }
}