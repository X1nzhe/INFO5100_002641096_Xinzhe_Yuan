/*
INFO5100 EXERCISE5
DATE: Dec/09/2023
STUDENT FULL NAME: Xinzhe Yuan
NUID: 002641096
 */

package org.neu.info5100;

import java.util.ArrayList;
import java.util.List;

//Singleton Pattern
public class Router implements RouterInterface{
    //create an object of SingleObject
    private static final Router instance = new Router();
    private RouterState currState;
    private final List<Observer> observers = new ArrayList<Observer>();
    //make the constructor private so that this class cannot be
    //instantiated
    private Router(){
        currState = new RouterOffState();
    }
    //Get the only object available
    public static Router getInstance(){
        return instance;
    }
    @Override
    public void setState(RouterState state){
        currState = state;
    }
    @Override
    public RouterState getState(){
        return currState;
    }
    @Override
    public void notifyObservers(String msg){
        for(Observer observer : observers){
            observer.update(msg);
        }
    }
    @Override
    public void powerOn(){

        if (getState() instanceof RouterOffState){
            changeState();
        }else{
            System.out.println("Router is already powered on.");
        }
    }

    @Override
    public void powerOff() {
        if (getState() instanceof RouterOnState){
            changeState();
        }else{
            System.out.println("Router is already powered off.");
        }
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
        observer.update(observer.getDeviceName() +" has been added to the Router");
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
        observer.update(observer.getDeviceName() +" has been removed from the Router");
    }
    @Override
    public void connectInternet(){
        System.out.println("Connected to Internet...");
        String stateMsg = currState.getClass().getSimpleName();
        notifyObservers("The router is in "+ stateMsg +" state and connected to Internet. ");
    }

    public void changeState(){
        currState.doAction(this);
    }

}
