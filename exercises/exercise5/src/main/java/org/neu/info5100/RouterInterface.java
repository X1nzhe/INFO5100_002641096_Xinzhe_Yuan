/*
INFO5100 EXERCISE5
DATE: Dec/09/2023
STUDENT FULL NAME: Xinzhe Yuan
NUID: 002641096
 */

package org.neu.info5100;

//Singleton Pattern
public interface RouterInterface {
    void setState(RouterState state);
    RouterState getState();

    void powerOn();

    void powerOff();

    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers(String msg);

    void connectInternet();
}
